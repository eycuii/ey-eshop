package com.ey.eshop.product.service.data.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.common.util.StringUtil;
import com.ey.eshop.product.dao.ProductDao;
import com.ey.eshop.product.model.entity.ProductDo;
import com.ey.eshop.product.service.data.ProductDataService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductDataServiceImpl
        extends ServiceImpl<ProductDao, ProductDo> implements ProductDataService {

    @Override
    public void add(String productSku, String productName, String productUnit, Double salePrice, Long userId) {
        if (StringUtil.isEmpty(productSku)) {
            throw new BusinessException("商品sku不能为空");
        }
        if (StringUtil.isEmpty(productName)) {
            throw new BusinessException("商品名称不能为空");
        }
        if (StringUtil.isEmpty(productUnit)) {
            throw new BusinessException("商品单位不能为空");
        }
        if (salePrice == null || salePrice < 0) {
            throw new BusinessException("商品销售价不能为空");
        }
        if (userId == null) {
            throw new BusinessException("用户不能为空");
        }
        if (getByProductSku(productSku) != null) {
            throw new BusinessException("商品sku已存在");
        }
        ProductDo productDo = ProductDo.builder()
                .productSku(productSku)
                .productName(productName)
                .productUnit(productUnit)
                .salePrice(salePrice)
                .userId(userId)
                .createTime(new Date())
                .build();
        save(productDo);
    }

    @Override
    public void update(Long id, String productName, String productUnit, Double salePrice) {
        if (id == null) {
            throw new BusinessException("商品ID不能为空");
        }
        if (StringUtil.isEmpty(productName)) {
            throw new BusinessException("商品名称不能为空");
        }
        if (StringUtil.isEmpty(productUnit)) {
            throw new BusinessException("商品单位不能为空");
        }
        if (salePrice == null || salePrice < 0) {
            throw new BusinessException("商品销售价不能为空");
        }
        boolean result = update(new LambdaUpdateWrapper<ProductDo>()
                .set(ProductDo::getProductName, productName)
                .set(ProductDo::getProductUnit, productUnit)
                .set(ProductDo::getSalePrice, salePrice)
                .eq(ProductDo::getId, id));
        if (!result) {
            throw new BusinessException("商品不存在");
        }
    }

    private ProductDo getByProductSku(String productSku) {
        return getOne(new LambdaQueryWrapper<ProductDo>().eq(ProductDo::getProductSku, productSku));
    }

    @Override
    public List<ProductDo> list() {
        return super.list();
    }

    @Override
    public ProductDo getById(Long id) {
        if (id == null) {
            throw new BusinessException("商品ID不能为空");
        }
        return super.getById(id);
    }
}
