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
@TableName("order_info")
public class OrderInfoDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 订单状态
     *
     * @see com.ey.eshop.order.enums.OrderStatusEnums
     */
    private Integer orderStatus;
    /**
     * 交易总金额
     */
    private Double totalAmount;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 取消时间
     */
    private Date cancelTime;
    /**
     * 支付时间
     */
    private Date payTime;
    /**
     * 退款时间
     */
    private Date returnTime;

    private Date createTime;
    private Date modifyTime;
}
