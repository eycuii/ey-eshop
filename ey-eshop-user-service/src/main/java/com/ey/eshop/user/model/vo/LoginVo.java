package com.ey.eshop.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

public class LoginVo {

    @Data
    @ApiModel("登录请求")
    public static class Request implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "用户名", required = true)
        private String userName;

        @ApiModelProperty(value = "密码", required = true)
        private String password;
    }

    @Data
    @Builder
    @ApiModel("登录响应")
    public static class Response implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "token", required = true)
        private String token;
    }
}
