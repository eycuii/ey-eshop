package com.ey.eshop.user.service.data.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.common.util.SecurityUtil;
import com.ey.eshop.common.util.StringUtil;
import com.ey.eshop.user.dao.UserDao;
import com.ey.eshop.user.model.entity.UserDo;
import com.ey.eshop.user.service.data.UserDataService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Validated
@Service
public class UserDataServiceImpl extends ServiceImpl<UserDao, UserDo> implements UserDataService {

    private static final int USER_NAME_MAX_LENGTH = 20;
    private static final int NICK_NAME_MAX_LENGTH = 20;

    @Override
    public UserDo getById(Long userId) {
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }
        return super.getById(userId);
    }

    @Override
    public UserDo getByUserName(String userName) {
        if (StringUtil.isEmpty(userName)) {
            throw new BusinessException("用户名不能为空");
        }
        return getOne(new LambdaQueryWrapper<UserDo>().eq(UserDo::getUserName, userName));
    }

    @Override
    public UserDo add(String userName, String nickName, String password) {
        if (StringUtil.isEmpty(userName)) {
            throw new BusinessException("用户名不能为空");
        }
        if (userName.length() > USER_NAME_MAX_LENGTH) {
            throw new BusinessException("用户名长度不能超过" + USER_NAME_MAX_LENGTH);
        }
        if (StringUtil.isEmpty(nickName)) {
            throw new BusinessException("昵称不能为空");
        }
        if (nickName.length() > NICK_NAME_MAX_LENGTH) {
            throw new BusinessException("昵称长度不能超过" + NICK_NAME_MAX_LENGTH);
        }
        if (StringUtil.isEmpty(password)) {
            throw new BusinessException("密码不能为空");
        }
        UserDo userDo = UserDo.builder()
                .userName(userName)
                .nickName(nickName)
                .password(SecurityUtil.md5(password))
                .createTime(new Date())
                .build();
        save(userDo);
        return userDo;
    }
}
