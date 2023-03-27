package com.ey.eshop.inventory.service.data;

import com.ey.eshop.inventory.model.entity.InventoryStockInDo;

public interface InventoryStockInDataService {

    InventoryStockInDo add(Long productId, Double stockInCount, Long userId);
}
