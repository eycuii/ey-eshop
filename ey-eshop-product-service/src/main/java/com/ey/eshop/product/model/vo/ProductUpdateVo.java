package com.ey.eshop.product.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

public class ProductUpdateVo {

    @ApiModel("商品编辑请求")
    @Data
    public static class Request implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "商品ID", required = true)
        private Long id;

        @ApiModelProperty(value = "商品名称", required = true)
        private String productName;

        @ApiModelProperty(value = "商品单位", required = true)
        private String productUnit;

        @ApiModelProperty(value = "商品销售价", required = true)
        private Double salePrice;
    }
}
