package com.ey.eshop.inventory.model.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.io.Serializable;

public class InventorySaleVo {

    @Data
    @Builder
    public static class Request implements Serializable {

        private static final long serialVersionUID = 1L;

        @Tolerate
        public Request() {
        }

        private Long orderItemId;
        private Long productId;
        private Double saleCount;
    }
}
