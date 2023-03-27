package com.ey.eshop.order.remote.client;

import com.ey.eshop.common.model.Result;
import com.ey.eshop.inventory.api.InventoryApi;
import com.ey.eshop.inventory.model.vo.InventorySaleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ey-eshop-inventory")
public interface InventoryClient extends InventoryApi {

    @Override
    @PostMapping("/api/inventory/deduct")
    Result<String> deduct(@RequestBody InventorySaleVo.Request request);
}
