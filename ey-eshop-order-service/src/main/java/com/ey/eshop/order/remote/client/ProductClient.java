package com.ey.eshop.order.remote.client;

import com.ey.eshop.common.model.Result;
import com.ey.eshop.product.api.ProductApi;
import com.ey.eshop.product.model.vo.ProductVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ey-eshop-product")
public interface ProductClient extends ProductApi {

    @Override
    @GetMapping("/api/product/getById")
    Result<ProductVo> getById(@RequestParam("id") Long id);
}
