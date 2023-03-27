package com.ey.eshop.common.util;

import cn.hutool.core.lang.UUID;

public class IdUtil {

    public static String uuid() {
        return UUID.fastUUID().toString(true);
    }
}
