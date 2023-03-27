package com.ey.eshop.order.service.data;

import com.ey.eshop.order.model.entity.OrderItemDo;

public interface OrderItemDataService {

    OrderItemDo add(Long orderId,
                    Long productId, String productSku, String productName,
                    Double salePrice, Double saleCount);
}
