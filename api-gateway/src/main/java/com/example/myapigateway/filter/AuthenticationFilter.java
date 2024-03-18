package com.example.myapigateway.filter;

import com.example.myapigateway.util.JwtTokenService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *  Gateway Filter
 *
 * JwtTokenFilter
 * JSON Web Token
 * 作用：检查token
 * added the JwtTokenFilter before the Spring Security internal UsernamePasswordAuthenticationFilter
 *  OncePerRequestFilter
 *  we need access to the user identity at this point to perform authentication/authorization,
 *  and its extraction happens inside the JWT token filter based on the provided JWT token.
 *  对每次客户端发来的HttpServletRequest 进行过滤
 *
 *  2. JWT实现认证和授权的原理
 *
 *  用户调用登录接口，登录成功后获取到JWT的token；
 *  之后用户每次调用接口都在http的header中添加一个叫Authorization的头，值为JWT的token；
 *  后台程序通过对Authorization头中信息的解码及数字签名校验来获取其中的用户信息，从而实现认证和授权。
 *
 */
@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    RouteValidator routeValidator;

//    @Autowired
//    RestTemplate restTemplate;
    @Autowired
    JwtTokenService jwtTokenService;
    /**
     *
     */
    public AuthenticationFilter() {
        super(Config.class);
    }

    /**
     * GatewayFilter
     */
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.info("GatewayFilter, 判断 URL 是否需要经过 Filter >>> ");
            //判断 URL 是否需要经过 Filter
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                //判断 header contains token or not
                log.info("判断 header contains token or not >>>>>>");
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header!");
                }
                //  获取 request的 authHeader, 去掉开头的 Bearer，从第7位开始 截取
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                log.info("authHeader>>>>>> "+ authHeader);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    // 调用Microservice, 验证 token
                    //REST call to AUTH service
                    //restTemplate.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                    // 使用本jar包的方法， jwtTokenService, 验证 token
                    jwtTokenService.validateToken(authHeader);

                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application!");
                }
            }
            // chain.filter
            return chain.filter(exchange);
        });
    }
    /**
     * 作用是什么？
     */
    public static class Config {

    }
}
