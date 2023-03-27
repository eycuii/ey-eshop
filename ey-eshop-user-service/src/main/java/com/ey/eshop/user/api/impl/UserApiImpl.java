package com.ey.eshop.user.api.impl;

import com.ey.eshop.common.model.Result;
import com.ey.eshop.user.api.UserApi;
import com.ey.eshop.user.service.biz.UserBizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
public class UserApiImpl implements UserApi {

    @Autowired
    private UserBizService userBizService;

    @GetMapping("/getUserNameById")
    @Override
    public Result<String> getUserNameById(@RequestParam Long id) {
        return Result.buildSuccess(userBizService.getUserNameById(id));
    }
}
