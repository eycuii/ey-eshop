package com.ey.eshop.common.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestContext {
    private String token;
    private Long userId;
}
