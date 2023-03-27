package com.ey.eshop.inventory.service.data.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.common.util.NumberUtil;
import com.ey.eshop.inventory.dao.InventoryDao;
import com.ey.eshop.inventory.model.entity.InventoryDo;
import com.ey.eshop.inventory.service.data.InventoryDataService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InventoryDataServiceImpl extends ServiceImpl<InventoryDao, InventoryDo> implements InventoryDataService {

    @Override
    public Double stockIn(Long productId, Double stockInCount) {
        if (productId == null) {
            throw new BusinessException("商品ID不能为空");
        }
        if (stockInCount == null) {
            throw new BusinessException("入库数量不能为空");
        }
        InventoryDo inventoryDo = getByProductId(productId);
        if (inventoryDo == null) {
            inventoryDo = InventoryDo.builder()
                    .productId(productId)
                    .stockCount(stockInCount)
                    .createTime(new Date())
                    .build();
            save(inventoryDo);
            return stockInCount;
        } else {
            double remainCount = NumberUtil.add(inventoryDo.getStockCount(), stockInCount);
            if (remainCount < 0) {
                throw new BusinessException("库存不足");
            }
            if (!update(new LambdaUpdateWrapper<InventoryDo>()
                    .set(InventoryDo::getStockCount, remainCount)
                    .set(InventoryDo::getModifyTime, new Date())
                    .eq(InventoryDo::getId, inventoryDo.getId()))) {
                throw new BusinessException("库存更新失败");
            }
            return remainCount;
        }
    }

    @Override
    public Double deductForSales(Long productId, Double saleCount) {
        if (productId == null) {
            throw new BusinessException("商品ID不能为空");
        }
        if (saleCount == null || saleCount <= 0) {
            throw new BusinessException("销售数量不能为空");
        }
        InventoryDo inventoryDo = getByProductId(productId);
        if (inventoryDo == null) {
            throw new BusinessException("库存不存在");
        }
        double remainCount = NumberUtil.sub(inventoryDo.getStockCount(), saleCount);
        if (remainCount < 0) {
            throw new BusinessException("库存不足");
        }
        if (!update(new LambdaUpdateWrapper<InventoryDo>()
                .set(InventoryDo::getStockCount, remainCount)
                .set(InventoryDo::getModifyTime, new Date())
                .eq(InventoryDo::getId, inventoryDo.getId()))) {
            throw new BusinessException("库存更新失败");
        }
        return remainCount;
    }

    @Override
    public List<InventoryDo> list() {
        return super.list();
    }

    private InventoryDo getByProductId(Long productId) {
        if (productId == null) {
            throw new BusinessException("商品ID不能为空");
        }
        return getOne(new LambdaQueryWrapper<InventoryDo>().eq(InventoryDo::getProductId, productId));
    }
}
