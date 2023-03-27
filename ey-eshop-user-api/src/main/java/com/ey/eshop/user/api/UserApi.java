package com.ey.eshop.user.api;

import com.ey.eshop.common.model.Result;

public interface UserApi {

    Result<String> getUserNameById(Long id);
}
