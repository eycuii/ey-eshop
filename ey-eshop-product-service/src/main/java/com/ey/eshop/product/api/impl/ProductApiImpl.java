package com.ey.eshop.product.api.impl;

import com.ey.eshop.common.model.Result;
import com.ey.eshop.product.api.ProductApi;
import com.ey.eshop.product.model.converter.ProductConverter;
import com.ey.eshop.product.model.dto.ProductDto;
import com.ey.eshop.product.model.vo.ProductVo;
import com.ey.eshop.product.service.biz.ProductBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductApiImpl implements ProductApi {

    @Autowired
    private ProductBizService productBizService;

    @Override
    @GetMapping("/getById")
    public Result<ProductVo> getById(@RequestParam Long id) {
        ProductDto productDto = productBizService.getById(id);
        return Result.buildSuccess(ProductConverter.INSTANCE.productDtoToVo(productDto));
    }
}
