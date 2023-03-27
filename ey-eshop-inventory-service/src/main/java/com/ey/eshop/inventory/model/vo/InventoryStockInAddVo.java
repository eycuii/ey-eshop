package com.ey.eshop.inventory.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

public class InventoryStockInAddVo {

    @ApiModel("创建入库单请求")
    @Data
    public static class Request implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "商品ID", required = true)
        private Long productId;

        @ApiModelProperty(value = "入库数量", required = true)
        private Double stockInCount;
    }
}
