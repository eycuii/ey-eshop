package com.ey.eshop.order.service.biz;

import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.common.util.CollectionUtil;
import com.ey.eshop.common.util.NumberUtil;
import com.ey.eshop.inventory.model.vo.InventorySaleVo;
import com.ey.eshop.order.model.converter.OrderConverter;
import com.ey.eshop.order.model.dto.OrderCreateRequest;
import com.ey.eshop.order.model.dto.OrderDto;
import com.ey.eshop.order.model.entity.OrderInfoDo;
import com.ey.eshop.order.model.entity.OrderItemDo;
import com.ey.eshop.order.remote.InventoryRemote;
import com.ey.eshop.order.remote.ProductRemote;
import com.ey.eshop.order.service.data.OrderInfoDataService;
import com.ey.eshop.order.service.data.OrderItemDataService;
import com.ey.eshop.product.model.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderBizService {

    @Autowired
    private OrderInfoDataService orderInfoDataService;
    @Autowired
    private OrderItemDataService orderItemDataService;
    @Autowired
    private InventoryRemote inventoryRemote;
    @Autowired
    private ProductRemote productRemote;

    @Transactional(rollbackFor = Throwable.class)
    public OrderDto add(OrderCreateRequest request) {
        if (request == null) {
            throw new BusinessException("订单信息不能为空");
        }
        List<OrderCreateRequest.Item> itemList = request.getItemList();
        if (CollectionUtil.isEmpty(itemList)) {
            throw new BusinessException("订单条目不能为空");
        }

        // 计算总金额
        double totalAmount = 0L;
        Map<Long, ProductVo> productVoMap = new HashMap<>(itemList.size());
        for (OrderCreateRequest.Item item : itemList) {
            ProductVo productVo = productRemote.getById(item.getProductId()).getData();
            if (productVo == null) {
                throw new BusinessException("商品不存在");
            }
            productVoMap.put(productVo.getId(), productVo);
            totalAmount = NumberUtil.add(totalAmount, NumberUtil.mul(productVo.getSalePrice(), item.getSaleCount()));
        }

        // 创建订单
        OrderInfoDo orderInfoDo = orderInfoDataService.addCreated(totalAmount, request.getUserId());

        for (OrderCreateRequest.Item item : itemList) {
            ProductVo productVo = productVoMap.get(item.getProductId());
            // 创建订单条目
            OrderItemDo orderItemDo = orderItemDataService.add(orderInfoDo.getId(),
                    productVo.getId(), productVo.getProductSku(), productVo.getProductName(),
                    productVo.getSalePrice(), item.getSaleCount());
            // 扣减库存
            inventoryRemote.deduct(InventorySaleVo.Request.builder()
                    .orderItemId(orderItemDo.getId())
                    .productId(orderItemDo.getProductId())
                    .saleCount(orderItemDo.getSaleCount())
                    .build());
        }
        return OrderConverter.INSTANCE.orderDoToOrderDto(orderInfoDo);
    }
}
