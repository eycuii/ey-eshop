package com.ey.eshop.user.service.biz;

import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.common.util.StringUtil;
import com.ey.eshop.user.model.entity.UserDo;
import com.ey.eshop.user.service.data.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBizService {

    @Autowired
    private UserDataService userDataService;

    public void add(String userName, String nickName, String password) {
        if (StringUtil.isEmpty(password)) {
            throw new BusinessException("密码不能为空");
        }
        if (userDataService.getByUserName(userName) != null) {
            throw new BusinessException("用户名已存在");
        }
        userDataService.add(userName, nickName, password);
    }

    public String getUserNameById(Long userId) {
        UserDo userDo = userDataService.getById(userId);
        if (userDo == null) {
            throw new BusinessException("用户不存在");
        }
        return userDo.getUserName();
    }
}
