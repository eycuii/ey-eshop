package com.ey.eshop.inventory.service.biz;

import com.ey.eshop.inventory.enums.InventoryLogSourceType;
import com.ey.eshop.inventory.model.converter.InventoryConverter;
import com.ey.eshop.inventory.model.dto.InventoryDto;
import com.ey.eshop.inventory.model.entity.InventoryDo;
import com.ey.eshop.inventory.service.data.InventoryDataService;
import com.ey.eshop.inventory.service.data.InventoryLogDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryBizService {

    @Autowired
    private InventoryDataService inventoryDataService;
    @Autowired
    private InventoryLogDataService inventoryLogDataService;

    @Transactional(rollbackFor = Throwable.class)
    public void deductFromSales(Long orderId, Long productId, Double saleCount) {
        inventoryDataService.deductForSales(productId, saleCount);
        inventoryLogDataService.add(InventoryLogSourceType.SALES, orderId, productId, saleCount);
    }

    public List<InventoryDto> list() {
        List<InventoryDo> inventoryDos = inventoryDataService.list();
        return InventoryConverter.INSTANCE.inventoryDosToInventoryDtos(inventoryDos);
    }
}
