package com.ey.eshop.user.service.biz;

import com.ey.eshop.common.exception.BusinessException;
import com.ey.eshop.common.util.JwtUtil;
import com.ey.eshop.common.util.SecurityUtil;
import com.ey.eshop.common.util.StringUtil;
import com.ey.eshop.user.model.entity.UserDo;
import com.ey.eshop.user.service.data.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginBizService {

    @Autowired
    private UserDataService userDataService;

    public String login(String userName, String password) {
        if (StringUtil.isEmpty(password)) {
            throw new BusinessException("密码不能为空");
        }
        UserDo userDO = userDataService.getByUserName(userName);
        if (userDO == null) {
            throw new BusinessException("用户名或密码错误");
        }
        String passwordMd5 = SecurityUtil.md5(password);
        if (!passwordMd5.equals(userDO.getPassword())) {
            throw new BusinessException("用户名或密码错误");
        }
        return JwtUtil.generateToken(userDO.getId());
    }
}
