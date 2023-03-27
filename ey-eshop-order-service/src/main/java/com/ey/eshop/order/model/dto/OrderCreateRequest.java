package com.ey.eshop.order.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderCreateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    private List<Item> itemList;

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
}
