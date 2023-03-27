package com.ey.eshop.order.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@TableName("order_item")
public class OrderItemDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商品sku
     */
    private String productSku;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 销售价
     */
    private Double salePrice;
    /**
     * 销售数量
     */
    private Double saleCount;
    /**
     * 销售金额
     */
    private Double saleAmount;

    private Date createTime;
    private Date modifyTime;
}
