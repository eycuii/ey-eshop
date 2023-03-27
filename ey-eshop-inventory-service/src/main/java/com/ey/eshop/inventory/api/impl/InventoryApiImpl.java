package com.ey.eshop.inventory.api.impl;

import com.ey.eshop.common.model.Result;
import com.ey.eshop.inventory.api.InventoryApi;
import com.ey.eshop.inventory.model.vo.InventorySaleVo;
import com.ey.eshop.inventory.service.biz.InventoryBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryApiImpl implements InventoryApi {

    @Autowired
    private InventoryBizService inventoryBizService;

    @Override
    @PostMapping("/deduct")
    public Result<String> deduct(@RequestBody InventorySaleVo.Request request) {
        inventoryBizService.deductFromSales(request.getOrderItemId(), request.getProductId(), request.getSaleCount());
        return Result.buildSuccess("操作成功");
    }
}
