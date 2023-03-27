package com.ey.eshop.product.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ProductVo implements Serializable {

    private static final long serialVersionUID = 1L;

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
