package com.ey.eshop.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ey.eshop.user.model.entity.UserDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends BaseMapper<UserDo> {
}
