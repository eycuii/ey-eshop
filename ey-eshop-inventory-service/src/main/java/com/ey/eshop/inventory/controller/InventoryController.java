package com.ey.eshop.inventory.controller;

import com.ey.eshop.common.controller.BaseController;
import com.ey.eshop.common.model.Result;
import com.ey.eshop.inventory.model.converter.InventoryConverter;
import com.ey.eshop.inventory.model.dto.InventoryDto;
import com.ey.eshop.inventory.model.vo.InventoryVo;
import com.ey.eshop.inventory.remote.ProductRemote;
import com.ey.eshop.inventory.service.biz.InventoryBizService;
import com.ey.eshop.product.model.vo.ProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "库存管理")
@RestController
@RequestMapping("/inventory")
public class InventoryController extends BaseController {

    @Autowired
    private InventoryBizService inventoryBizService;
    @Autowired
    private ProductRemote productRemote;

    @ApiOperation("库存列表")
    @GetMapping("/list")
    public Result<List<InventoryVo>> list() {
        List<InventoryDto> dtoList = inventoryBizService.list();
        List<InventoryVo> voList = InventoryConverter.INSTANCE.inventoryDtosToInventoryVs(dtoList);
        for (InventoryVo vo : voList) {
            ProductVo productVo = productRemote.getById(vo.getProductId()).getData();
            if (productVo != null) {
                vo.setProductSku(productVo.getProductSku());
                vo.setProductName(productVo.getProductName());
            }
        }
        return Result.buildSuccess(voList);
    }
}
