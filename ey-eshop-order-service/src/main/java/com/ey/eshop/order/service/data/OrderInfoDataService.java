package com.ey.eshop.order.service.data;

import com.ey.eshop.order.model.entity.OrderInfoDo;

public interface OrderInfoDataService {

    OrderInfoDo addCreated(Double totalAmount, Long userId);
}
