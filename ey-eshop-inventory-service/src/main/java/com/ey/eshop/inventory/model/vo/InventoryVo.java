package com.ey.eshop.inventory.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InventoryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
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
     * 库存数量
     */
    private Double stockCount;

    private Date createTime;
    private Date modifyTime;
}
