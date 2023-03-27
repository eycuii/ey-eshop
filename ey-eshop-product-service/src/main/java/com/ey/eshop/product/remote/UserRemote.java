package com.ey.eshop.product.remote;

import com.ey.eshop.common.model.Result;
import com.ey.eshop.common.remote.BaseRemote;
import com.ey.eshop.product.remote.client.UserClient;
import com.ey.eshop.user.api.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRemote extends BaseRemote implements UserApi {

    @Autowired
    private UserClient userClient;

    @Override
    public Result<String> getUserNameById(Long id) {
        Result<String> result = userClient.getUserNameById(id);
        return handleResult(result);
    }
}
