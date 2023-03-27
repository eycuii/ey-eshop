package com.ey.eshop.user.service.data;

import com.ey.eshop.user.model.entity.UserDo;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserDataService {

    UserDo getById(Long userId);

    UserDo getByUserName(String userName);

    UserDo add(String userName, String nickName, String password);
}
