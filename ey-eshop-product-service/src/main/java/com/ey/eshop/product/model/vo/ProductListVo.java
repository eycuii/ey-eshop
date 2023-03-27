package com.ey.eshop.product.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

public class ProductListVo {

    @ApiModel("商品列表响应")
    @Data
    @Builder
    public static class Response implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "商品ID", required = true)
        private Long id;

        @ApiModelProperty(value = "商品sku", required = true)
        private String productSku;

        @ApiModelProperty(value = "商品名称", required = true)
        private String productName;

        @ApiModelProperty(value = "商品单位", required = true)
        private String productUnit;

        @ApiModelProperty(value = "商品销售价", required = true)
        private Double salePrice;

        @ApiModelProperty(value = "用户ID", required = true)
        private Long userId;

        @ApiModelProperty(value = "用户名", required = true)
        private String userName;

        @ApiModelProperty(value = "创建时间", required = true)
        private String createTime;
    }
}
