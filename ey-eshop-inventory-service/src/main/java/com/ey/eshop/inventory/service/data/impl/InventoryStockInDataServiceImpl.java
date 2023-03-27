package com.ey.eshop.inventory.service.data.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.inventory.dao.InventoryStockInDao;
import com.ey.eshop.inventory.model.entity.InventoryStockInDo;
import com.ey.eshop.inventory.service.data.InventoryStockInDataService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InventoryStockInDataServiceImpl
        extends ServiceImpl<InventoryStockInDao, InventoryStockInDo> implements InventoryStockInDataService {

    @Override
    public InventoryStockInDo add(Long productId, Double stockInCount, Long userId) {
        if (productId == null) {
            throw new BusinessException("商品ID不能为空");
        }
        if (stockInCount == null || stockInCount <= 0) {
            throw new BusinessException("入库数量不能为空");
        }
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }
        InventoryStockInDo inventoryStockInDo = InventoryStockInDo.builder()
                .productId(productId)
                .stockInCount(stockInCount)
                .userId(userId)
                .createTime(new Date())
                .build();
        save(inventoryStockInDo);
        return inventoryStockInDo;
    }
}
