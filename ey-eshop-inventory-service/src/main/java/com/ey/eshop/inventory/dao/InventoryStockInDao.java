package com.ey.eshop.inventory.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ey.eshop.inventory.model.entity.InventoryStockInDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InventoryStockInDao extends BaseMapper<InventoryStockInDo> {
}
