package com.ey.eshop.order.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ey.eshop.order.model.entity.OrderInfoDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoDao extends BaseMapper<OrderInfoDo> {
}
