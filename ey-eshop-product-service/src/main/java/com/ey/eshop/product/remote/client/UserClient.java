package com.ey.eshop.product.remote.client;

import com.ey.eshop.common.model.Result;
import com.ey.eshop.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ey-eshop-user")
public interface UserClient extends UserApi {

    @Override
    @GetMapping("/api/user/getUserNameById")
    Result<String> getUserNameById(@RequestParam("id") Long id);
}
