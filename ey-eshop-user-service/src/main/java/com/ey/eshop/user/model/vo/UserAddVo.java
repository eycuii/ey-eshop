package com.ey.eshop.user.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

public class UserAddVo {

    @Data
    @ApiModel("用户新增请求")
    public static class Request implements Serializable {

        private static final long serialVersionUID = 1L;

        @ApiModelProperty(value = "用户名", required = true)
        private String userName;

        @ApiModelProperty(value = "昵称", required = true)
        private String nickName;

        @ApiModelProperty(value = "密码", required = true)
        private String password;
    }
}
