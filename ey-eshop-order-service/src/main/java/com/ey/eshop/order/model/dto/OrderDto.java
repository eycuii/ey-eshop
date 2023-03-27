package com.ey.eshop.order.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 商品sku
     */
    private String productSku;
    /**
     * 购买数量
     */
    private Double purchaseCount;
    /**
     * 用户ID
     */
    private Long userId;

    private Date createTime;
    private Date modifyTime;
}
