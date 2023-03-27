package com.ey.eshop.product.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;

public class ProductAddVo {

    @ApiModel("商品新增请求")
    @Data
    @Builder
    public static class Request implements Serializable {

        private static final long serialVersionUID = 1L;

        @Tolerate
        public Request() {
        }

        @ApiModelProperty(value = "商品sku", required = true)
        private String productSku;

        @ApiModelProperty(value = "商品名称", required = true)
        private String productName;

        @ApiModelProperty(value = "商品单位", required = true)
        private String productUnit;

        @ApiModelProperty(value = "商品销售价", required = true)
        private Double salePrice;
    }
}
