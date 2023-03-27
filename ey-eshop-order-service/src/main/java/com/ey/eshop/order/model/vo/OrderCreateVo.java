package com.ey.eshop.order.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderCreateVo {

    @Data
    @ApiModel("下单请求")
    public static class Request implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "订单条目", required = true)
        private List<Item> itemList;
    }

    @ApiModel("下单请求订单条目")
    @Data
    public static class Item implements Serializable {

        private static final long serialVersionUID = 1L;
        /**
         * 商品ID
         */
        private Long productId;
        /**
         * 销售数量
         */
        private Double saleCount;
    }

    @Data
    @Builder
    @ApiModel("下单响应")
    public static class Response implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "订单号", required = true)
        private String orderNo;

        @ApiModelProperty(value = "创建时间", required = true)
        private Date createTime;
    }
}
