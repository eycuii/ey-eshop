package com.ey.eshop.order.remote;

import com.ey.eshop.common.model.Result;
import com.ey.eshop.common.remote.BaseRemote;
import com.ey.eshop.order.remote.client.ProductClient;
import com.ey.eshop.product.api.ProductApi;
import com.ey.eshop.product.model.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRemote extends BaseRemote implements ProductApi {

    @Autowired
    private ProductClient productClient;

    @Override
    public Result<ProductVo> getById(Long id) {
        Result<ProductVo> result = productClient.getById(id);
        return handleResult(result);
    }
}
