package com.ey.eshop.user.controller;

import com.ey.eshop.common.controller.BaseController;
import com.ey.eshop.common.model.Result;
import com.ey.eshop.user.model.vo.UserAddVo;
import com.ey.eshop.user.service.biz.UserBizService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserBizService userBizService;

    @ApiOperation("用户新增")
    @PostMapping("/add")
    public Result<String> add(@RequestBody UserAddVo.Request request) {
        userBizService.add(request.getUserName(), request.getNickName(), request.getPassword());
        return Result.buildSuccess("操作成功");
    }
}
