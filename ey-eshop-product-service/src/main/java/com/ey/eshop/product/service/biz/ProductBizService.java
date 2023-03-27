package com.ey.eshop.product.service.biz;

import com.ey.eshop.product.model.converter.ProductConverter;
import com.ey.eshop.product.model.dto.ProductDto;
import com.ey.eshop.product.model.entity.ProductDo;
import com.ey.eshop.product.service.data.ProductDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBizService {

    @Autowired
    private ProductDataService productDataService;

    public void add(String productSku, String productName, String productUnit, Double salePrice, Long userId) {
        productDataService.add(productSku, productName, productUnit, salePrice, userId);
    }

    public void update(Long id, String productName, String productUnit, Double salePrice) {
        productDataService.update(id, productName, productUnit, salePrice);
    }

    public List<ProductDto> list() {
        List<ProductDo> doList = productDataService.list();
        return ProductConverter.INSTANCE.productDosToProductDtos(doList);
    }

    public ProductDto getById(Long id) {
        ProductDo productDo = productDataService.getById(id);
        return ProductConverter.INSTANCE.productDoToDto(productDo);
    }
}
