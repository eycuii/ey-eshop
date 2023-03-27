package com.ey.eshop.inventory.api;

import com.ey.eshop.common.model.Result;
import com.ey.eshop.inventory.model.vo.InventorySaleVo;

public interface InventoryApi {

    Result<String> deduct(InventorySaleVo.Request request);
}
