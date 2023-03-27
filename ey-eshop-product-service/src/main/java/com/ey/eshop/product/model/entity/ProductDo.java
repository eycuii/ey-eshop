package com.ey.eshop.product.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@TableName("product")
public class ProductDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 商品sku
     */
    private String productSku;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品单位
     */
    private String productUnit;
    /**
     * 商品销售价
     */
    private Double salePrice;
    /**
     * 用户ID
     */
    private Long userId;

    private Date createTime;
    private Date modifyTime;
}