package com.ey.eshop.inventory.enums;

public enum InventoryLogSourceType {

    /**
     * 入库
     */
    STOCK_IN(1, true),
    /**
     * 出库
     */
    STOCK_OUT(2, false),
    /**
     * 销售
     */
    SALES(3, false),
    /**
     * 退货
     */
    RETURN(4, true);

    private final int value;
    private final boolean forAddStock;

    InventoryLogSourceType(int value, boolean forAddStock) {
        this.value = value;
        this.forAddStock = forAddStock;
    }

    public int getValue() {
        return value;
    }

    public boolean isForAddStock() {
        return forAddStock;
    }
}
