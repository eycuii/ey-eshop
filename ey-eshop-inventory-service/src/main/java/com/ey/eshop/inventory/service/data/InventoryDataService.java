package com.ey.eshop.inventory.service.data;

import com.ey.eshop.inventory.model.entity.InventoryDo;

import java.util.List;

public interface InventoryDataService {

    Double stockIn(Long productId, Double stockInCount);

    Double deductForSales(Long productId, Double saleCount);

    List<InventoryDo> list();
}
