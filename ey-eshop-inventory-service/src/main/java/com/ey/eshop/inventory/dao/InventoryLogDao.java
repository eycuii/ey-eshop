package com.ey.eshop.inventory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ey.eshop.inventory.model.entity.InventoryLogDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryLogDao extends BaseMapper<InventoryLogDo> {
}
