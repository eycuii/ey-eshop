package com.ey.eshop.product.controller;

import com.ey.eshop.common.controller.BaseController;
import com.ey.eshop.common.model.Result;
import com.ey.eshop.product.model.converter.ProductConverter;
import com.ey.eshop.product.model.dto.ProductDto;
import com.ey.eshop.product.model.vo.ProductAddVo;
import com.ey.eshop.product.model.vo.ProductListVo;
import com.ey.eshop.product.model.vo.ProductUpdateVo;
import com.ey.eshop.product.remote.UserRemote;
import com.ey.eshop.product.service.biz.ProductBizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "商品管理")
@RestController
@RequestMapping("/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductBizService productBizService;
    @Autowired
    private UserRemote userRemote;

    @ApiOperation("商品新增")
    @PostMapping("/add")
    public Result<String> add(@RequestBody ProductAddVo.Request request) {
        Long userId = getCurrentUserId();
        productBizService.add(request.getProductSku(), request.getProductName(),
                request.getProductUnit(), request.getSalePrice(), userId);
        return Result.buildSuccess("操作成功");
    }

    @ApiOperation("商品编辑")
    @PostMapping("/update")
    public Result<String> update(@RequestBody ProductUpdateVo.Request request) {
        productBizService.update(request.getId(), request.getProductName(),
                request.getProductUnit(), request.getSalePrice());
        return Result.buildSuccess("操作成功");
    }

    @ApiOperation("商品列表")
    @GetMapping("/list")
    public Result<List<ProductListVo.Response>> list() {
        List<ProductDto> dtoList = productBizService.list();
        List<ProductListVo.Response> voList = ProductConverter.INSTANCE.productWithUserDtosToVos(dtoList);
        for (ProductListVo.Response vo : voList) {
            String userName = userRemote.getUserNameById(vo.getUserId()).getData();
            vo.setUserName(userName);
        }
        return Result.buildSuccess(voList);
    }
}
