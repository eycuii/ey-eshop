package com.ey.eshop.order.remote;

import com.ey.eshop.common.model.Result;
import com.ey.eshop.common.remote.BaseRemote;
import com.ey.eshop.inventory.api.InventoryApi;
import com.ey.eshop.inventory.model.vo.InventorySaleVo;
import com.ey.eshop.order.remote.client.InventoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryRemote extends BaseRemote implements InventoryApi {

    @Autowired
    private InventoryClient inventoryClient;

    @Override
    public Result<String> deduct(InventorySaleVo.Request request) {
        Result<String> result = inventoryClient.deduct(request);
        return handleResult(result);
    }
}
