package com.example.myidentityservice.service;

import com.example.myidentityservice.entity.UserCredential;
import com.example.myidentityservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Authentication Using JWT with Spring Security
 *
 */
@Service
public class AuthService {
    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenService jwtTokenService;

    public String saveUser(UserCredential userCredential){
        //对用户密码进行加密存储到数据库
        userCredential.setPassword(passwordEncoder.encode(userCredential.getPassword()));
        userCredentialRepository.save(userCredential);
        return "user Added!";

    }

    /**
     * 使用JWT 根据userName 生成 Token
     */
    public String generateToken(String userName){
        return jwtTokenService.generateToken(userName);
    }

    /**
     * 验证 Token
     */
    public void validateToken(String token){
        jwtTokenService.validateToken(token);
    }

}
