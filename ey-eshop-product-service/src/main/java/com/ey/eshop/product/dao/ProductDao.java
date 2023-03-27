package com.ey.eshop.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ey.eshop.product.model.entity.ProductDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDao extends BaseMapper<ProductDo> {
}
