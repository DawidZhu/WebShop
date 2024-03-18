package com.example.myidentityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 通过填写用户名和密码登录。
 * 验证成功后, 服务端生成 JWT 认证 token, 并返回给客户端。
 * 验证失败后返回错误信息。
 * 客户端在每次请求中携带 JWT 来访问权限内的接口。
 * 每次请求验证 token 有效性和权限，在无有效 token 时抛出 401 未授权错误。
 *
 * springSecurity
 * 原理其实就是一个过滤器链，内部包含了提供各种功能的过滤器。
 * When you add the Spring Security framework to your application,
 * it automatically registers a filters chain that intercepts all incoming requests.
 *
 * authentication 认证, refers to the process of verifying the identity of a user
 * authorization  授权, refers to the process of determining if a user has proper permission
 * to perform a particular action or read particular data
 *
 * 1.Spring Security Filters Chain
 * 2.AuthenticationManager, as a coordinator where you can register multiple providers,
 * 3.AuthenticationProvider, processes specific types of authentication.
 * 4. UserDetailsService, is described as a core interface that loads user
 *
 *
 * 1.register
 * 2.generateToken
 * 3.validateToken
 */
@SpringBootApplication
@EnableEurekaClient
// @EnableDiscoveryClient //spring 3.0 换名了？
public class  MyIdentityServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyIdentityServiceApplication.class, args);
	}

}
