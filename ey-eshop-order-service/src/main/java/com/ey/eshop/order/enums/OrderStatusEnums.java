package com.ey.eshop.order.enums;

public enum OrderStatusEnums {

    CREATED(1),
    PAID(2),
    RETURNED(3),
    CANCELED(4);

    private final int value;

    OrderStatusEnums(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
