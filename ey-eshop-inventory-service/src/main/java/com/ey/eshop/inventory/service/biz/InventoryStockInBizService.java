package com.ey.eshop.inventory.service.biz;

import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.inventory.enums.InventoryLogSourceType;
import com.ey.eshop.inventory.model.entity.InventoryStockInDo;
import com.ey.eshop.inventory.remote.ProductRemote;
import com.ey.eshop.inventory.service.data.InventoryDataService;
import com.ey.eshop.inventory.service.data.InventoryLogDataService;
import com.ey.eshop.inventory.service.data.InventoryStockInDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryStockInBizService {

    @Autowired
    private InventoryStockInDataService inventoryStockInDataService;
    @Autowired
    private InventoryDataService inventoryDataService;
    @Autowired
    private InventoryLogDataService inventoryLogDataService;
    @Autowired
    private ProductRemote productRemote;

    @Transactional(rollbackFor = Throwable.class)
    public void stockIn(Long productId, Double stockInCount, Long userId) {
        if (productRemote.getById(productId).getData() == null) {
            throw new BusinessException("商品不存在");
        }
        // 创建入库单
        InventoryStockInDo inventoryStockInDo = inventoryStockInDataService.add(productId, stockInCount, userId);
        // 增加库存
        inventoryDataService.stockIn(productId, stockInCount);
        // 库存日志
        inventoryLogDataService.add(InventoryLogSourceType.STOCK_IN, inventoryStockInDo.getId(),
                productId, stockInCount);
    }
}
