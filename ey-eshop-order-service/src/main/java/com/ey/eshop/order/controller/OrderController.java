package com.ey.eshop.order.controller;

import com.ey.eshop.common.controller.BaseController;
import com.ey.eshop.common.model.Result;
import com.ey.eshop.order.model.converter.OrderConverter;
import com.ey.eshop.order.model.dto.OrderCreateRequest;
import com.ey.eshop.order.model.dto.OrderDto;
import com.ey.eshop.order.model.vo.OrderCreateVo;
import com.ey.eshop.order.service.biz.OrderBizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "订单管理")
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderBizService orderBizService;

    @ApiOperation("下单")
    @PostMapping("/create")
    public Result<OrderCreateVo.Response> create(@RequestBody OrderCreateVo.Request request) {
        Long userId = getCurrentUserId();
        OrderDto orderDto = orderBizService.add(
                OrderConverter.INSTANCE.orderCreateVoToOrderCreateRequest(request, userId));
        return Result.buildSuccess(OrderCreateVo.Response.builder()
                .orderNo(orderDto.getOrderNo())
                .createTime(orderDto.getCreateTime())
                .build());
    }
}
