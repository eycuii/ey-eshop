package com.ey.eshop.order.service.data.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.common.util.NumberUtil;
import com.ey.eshop.common.util.StringUtil;
import com.ey.eshop.order.dao.OrderItemDao;
import com.ey.eshop.order.model.entity.OrderItemDo;
import com.ey.eshop.order.service.data.OrderItemDataService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderItemDataServiceImpl extends ServiceImpl<OrderItemDao, OrderItemDo> implements OrderItemDataService {

    @Override
    public OrderItemDo add(Long orderId,
                           Long productId, String productSku, String productName,
                           Double salePrice, Double saleCount) {
        if (orderId == null) {
            throw new BusinessException("订单ID不能为空");
        }
        if (productId == null) {
            throw new BusinessException("商品ID不能为空");
        }
        if (StringUtil.isEmpty(productSku)) {
            throw new BusinessException("商品sku不能为空");
        }
        if (StringUtil.isEmpty(productName)) {
            throw new BusinessException("商品名称不能为空");
        }
        if (salePrice == null || salePrice < 0) {
            throw new BusinessException("销售价不能为空");
        }
        if (saleCount == null || saleCount <= 0) {
            throw new BusinessException("销售数量不能为空");
        }
        OrderItemDo orderItemDo = OrderItemDo.builder()
                .orderId(orderId)
                .productId(productId)
                .productSku(productSku)
                .productName(productName)
                .salePrice(salePrice)
                .saleCount(saleCount)
                .saleAmount(NumberUtil.mul(salePrice, saleCount))
                .createTime(new Date())
                .build();
        save(orderItemDo);
        return orderItemDo;
    }
}
