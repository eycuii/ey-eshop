package com.ey.eshop.inventory.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class InventoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 库存数量
     */
    private Double stockCount;

    private Date createTime;
    private Date modifyTime;
}
