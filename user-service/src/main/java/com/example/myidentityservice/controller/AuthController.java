package com.example.myidentityservice.controller;

import com.example.myidentityservice.dto.AuthRequest;
import com.example.myidentityservice.entity.UserCredential;
import com.example.myidentityservice.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * we verify the provided credentials using the authentication manager
 * and in case of success, we generate the JWT token and return it as a response header
 * along with the user identity information in the response body.
 *
 *  2. JWT实现认证和授权的原理
 *
 *  用户调用登录接口，登录成功后获取到JWT的token；
 *  之后用户每次调用接口都在http的header中添加一个叫Authorization的头，值为JWT的token；
 *  后台程序通过对Authorization头中信息的解码及数字签名校验来获取其中的用户信息，从而实现认证和授权。
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;
    //AuthenticationManager, as a coordinator where you can register multiple providers,
    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public String addUser(@RequestBody UserCredential userCredential){

        return authService.saveUser(userCredential);
    }
    /**
     * 生成 Token
     * 对 用户名,密码进行校验
     * Spring Security 默认自带表单登录，负责处理这个登录验证过程的过滤器叫“UsernamePasswordAuthenticationFilter”，
     * 不过它只支持表单传值，这里用自定义的类继承它，使其能够支持 JSON 传值，负责登录验证接口。
     */
    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){
        log.info("getToken >>>" + authRequest.getUsername());
        //从 DB 查询 name, password ？？？
        //查逻辑？
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken
                        (authRequest.getUsername(),authRequest.getPassword()));
        //如果用户，密码验证通过，生成token
        if(authentication.isAuthenticated()){
            return authService.generateToken(authRequest.getUsername());
        }else
            throw new RuntimeException("invalid access!");

    }

    /**
     * 验证 Token
     * 不能使用pathVariable 传值? 太长了？
     * 使用RequestParam
     * http://127.0.0.1:9898/auth/validate?token=
     */
    @GetMapping("/validate")

    public String validateToken(@RequestParam("token") String token){
        log.info("Token >>>" + token);
         authService.validateToken(token);
        return "Token is valid!";
    }


}
