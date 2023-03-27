package com.ey.eshop.user.controller;

import com.ey.eshop.common.controller.BaseController;
import com.ey.eshop.common.model.Result;
import com.ey.eshop.user.model.vo.LoginVo;
import com.ey.eshop.user.service.biz.LoginBizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "登录管理")
@RestController
@RequestMapping("/")
public class LoginController extends BaseController {

    @Autowired
    private LoginBizService loginBizService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<LoginVo.Response> login(@RequestBody LoginVo.Request request) {
        String token = loginBizService.login(request.getUserName(), request.getPassword());
        return Result.buildSuccess(LoginVo.Response.builder().token(token).build());
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public Result<String> logout() {
        // 让客户端清除token
        return Result.buildSuccess("操作成功");
    }
}
