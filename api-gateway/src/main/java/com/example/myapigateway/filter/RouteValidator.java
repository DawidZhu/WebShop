package com.example.myapigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

/**
 *
 */
@Component
public class RouteValidator {

    /**
     * 列出指定的路径url
     * 可不需要经过 Filter
     */
    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/token",
            "/eureka"
    );

    /**
     *  Predicate 函数式接口
     *  判断请求的url 是否包含指定的路径
     *
     */
    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
