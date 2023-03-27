package com.ey.eshop.product.api;

import com.ey.eshop.common.model.Result;
import com.ey.eshop.product.model.vo.ProductVo;

public interface ProductApi {

    Result<ProductVo> getById(Long id);
}
