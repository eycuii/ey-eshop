package com.ey.eshop.inventory.service.data.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.inventory.dao.InventoryLogDao;
import com.ey.eshop.inventory.enums.InventoryLogSourceType;
import com.ey.eshop.inventory.model.entity.InventoryLogDo;
import com.ey.eshop.inventory.service.data.InventoryLogDataService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class InventoryLogDataServiceImpl
        extends ServiceImpl<InventoryLogDao, InventoryLogDo> implements InventoryLogDataService {

    @Override
    public void add(InventoryLogSourceType sourceType, Long sourceId,
                    Long productId, Double logCount) {
        if (sourceType == null) {
            throw new BusinessException("来源类型不能为空");
        }
        if (sourceId == null) {
            throw new BusinessException("来源ID不能为空");
        }
        if (productId == null) {
            throw new BusinessException("商品ID不能为空");
        }
        if (logCount == null) {
            throw new BusinessException("记录数量不能为空");
        }
        InventoryLogDo inventoryLogDo = InventoryLogDo.builder()
                .sourceType(sourceType.getValue())
                .sourceId(sourceId)
                .productId(productId)
                .logCount(sourceType.isForAddStock() ?
                        logCount : BigDecimal.valueOf(logCount).negate().doubleValue())
                .createTime(new Date())
                .build();
        save(inventoryLogDo);
    }
}
