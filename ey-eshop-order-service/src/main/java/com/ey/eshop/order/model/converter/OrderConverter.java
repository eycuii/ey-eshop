package com.ey.eshop.order.model.converter;

import com.ey.eshop.order.model.dto.OrderCreateRequest;
import com.ey.eshop.order.model.dto.OrderDto;
import com.ey.eshop.order.model.entity.OrderInfoDo;
import com.ey.eshop.order.model.vo.OrderCreateVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderConverter {

    OrderConverter INSTANCE = Mappers.getMapper(OrderConverter.class);

    OrderDto orderDoToOrderDto(OrderInfoDo orderInfoDo);

    OrderCreateRequest orderCreateVoToOrderCreateRequest(OrderCreateVo.Request request, Long userId);
}
