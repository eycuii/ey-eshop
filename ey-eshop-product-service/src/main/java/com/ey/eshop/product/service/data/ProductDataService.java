package com.ey.eshop.product.service.data;

import com.ey.eshop.product.model.entity.ProductDo;

import java.util.List;

public interface ProductDataService {

    void add(String productSku, String productName, String productUnit, Double salePrice, Long userId);

    void update(Long id, String productName, String productUnit, Double salePrice);

    List<ProductDo> list();

    ProductDo getById(Long id);
}
