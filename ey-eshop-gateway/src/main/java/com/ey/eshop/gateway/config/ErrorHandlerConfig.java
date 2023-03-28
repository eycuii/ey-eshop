package com.ey.eshop.gateway.config;

import com.ey.eshop.common.enums.CommonResultCodeEnum;
import com.ey.eshop.common.exception.BaseException;
import com.ey.eshop.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class ErrorHandlerConfig {

    private static final String ERROR_INTERNAL_ATTRIBUTE = ErrorHandlerConfig.class.getName() + ".ERROR";

    @Bean
    public ErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes() {
            @Override
            public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
                Throwable error = getError(request);
                Map<String, Object> errorAttributes = new HashMap<>(1);
                errorAttributes.put("error", error);
                return errorAttributes;
            }

            @Override
            public Throwable getError(ServerRequest request) {
                Optional<Object> error = request.attribute(ERROR_INTERNAL_ATTRIBUTE);
                error.ifPresent((value) -> request.attributes().putIfAbsent(ErrorAttributes.ERROR_ATTRIBUTE, value));
                return (Throwable) error.orElseThrow(() ->
                        new IllegalStateException("Missing exception attribute in ServerWebExchange"));
            }

            @Override
            public void storeErrorInformation(Throwable error, ServerWebExchange exchange) {
                exchange.getAttributes().putIfAbsent(ERROR_INTERNAL_ATTRIBUTE, error);
            }
        };
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes,
                                                             WebProperties webProperties,
                                                             ObjectProvider<ViewResolver> viewResolvers,
                                                             ServerCodecConfigurer serverCodecConfigurer,
                                                             ApplicationContext applicationContext) {
        AbstractErrorWebExceptionHandler handler = new AbstractErrorWebExceptionHandler(
                errorAttributes, webProperties.getResources(), applicationContext) {

            @Override
            protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
                return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
            }

            private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
                Map<String, Object> errorAttributes = getErrorAttributes(request, null);
                Throwable error = (Throwable) errorAttributes.get("error");
                HttpStatus errorStatus = error instanceof ResponseStatusException ?
                        ((ResponseStatusException) error).getStatus() : HttpStatus.INTERNAL_SERVER_ERROR;
                log.error("errorStatus: {}, errorMsg: {}", errorStatus.value(), error.getMessage(), error);

                String code = CommonResultCodeEnum.SYSTEM_ERROR.getCode();
                String msg = CommonResultCodeEnum.SYSTEM_ERROR.getMessage();
                if (error instanceof BaseException) {
                    BaseException exception = (BaseException) error;
                    code = exception.getCode();
                    msg = exception.getMessage();
                }

                return ServerResponse
                        .status(errorStatus.value())
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(Result.buildError(code, msg)));
            }
        };
        handler.setViewResolvers(viewResolvers.orderedStream().collect(Collectors.toList()));
        handler.setMessageWriters(serverCodecConfigurer.getWriters());
        handler.setMessageReaders(serverCodecConfigurer.getReaders());
        return handler;
    }
}
