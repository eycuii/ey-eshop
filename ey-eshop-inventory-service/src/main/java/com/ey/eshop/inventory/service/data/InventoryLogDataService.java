package com.ey.eshop.inventory.service.data;

import com.ey.eshop.inventory.enums.InventoryLogSourceType;

public interface InventoryLogDataService {

    void add(InventoryLogSourceType sourceType, Long sourceId,
             Long productId, Double logCount);
}
