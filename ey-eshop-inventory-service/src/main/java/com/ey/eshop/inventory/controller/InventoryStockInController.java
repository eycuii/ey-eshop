package com.ey.eshop.inventory.controller;

import com.ey.eshop.common.controller.BaseController;
import com.ey.eshop.common.model.Result;
import com.ey.eshop.inventory.model.vo.InventoryStockInAddVo;
import com.ey.eshop.inventory.service.biz.InventoryStockInBizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "入库单管理")
@RestController
@RequestMapping("/inventoryStockIn")
public class InventoryStockInController extends BaseController {

    @Autowired
    private InventoryStockInBizService inventoryStockInBizService;

    @ApiOperation("创建入库单")
    @PostMapping("/add")
    public Result<String> deduct(@RequestBody InventoryStockInAddVo.Request request) {
        Long userId = getCurrentUserId();
        inventoryStockInBizService.stockIn(request.getProductId(), request.getStockInCount(), userId);
        return Result.buildSuccess("操作成功");
    }
}
