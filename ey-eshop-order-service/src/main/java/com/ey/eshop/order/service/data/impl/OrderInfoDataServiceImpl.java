package com.ey.eshop.order.service.data.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.common.util.IdUtil;
import com.ey.eshop.order.dao.OrderInfoDao;
import com.ey.eshop.order.enums.OrderStatusEnums;
import com.ey.eshop.order.model.entity.OrderInfoDo;
import com.ey.eshop.order.service.data.OrderInfoDataService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderInfoDataServiceImpl extends ServiceImpl<OrderInfoDao, OrderInfoDo> implements OrderInfoDataService {

    @Override
    public OrderInfoDo addCreated(Double totalAmount, Long userId) {
        if (totalAmount == null || totalAmount < 0) {
            throw new BusinessException("交易总金额不能为空");
        }
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }
        OrderInfoDo orderInfoDo = OrderInfoDo.builder()
                .orderNo(IdUtil.uuid())
                .orderStatus(OrderStatusEnums.CREATED.getValue())
                .totalAmount(totalAmount)
                .userId(userId)
                .createTime(new Date())
                .build();
        save(orderInfoDo);
        return orderInfoDo;
    }
}
