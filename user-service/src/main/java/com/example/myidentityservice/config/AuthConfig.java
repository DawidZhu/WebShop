package com.example.myidentityservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * EnableWebSecurity
 * SpringSecurity 采用的是责任链 Chain of Responsibility 的设计模式，是一堆过滤器链的组合，它有一条很长的过滤器链。
 *
 * Spring Security是一个能够为基于Spring的企业应用系统提供声明式的安全访问控制解决方案的安全框架。
 * 它提供了一组可以在Spring应用上下文中配置的Bean，充分利用了Spring IoC，DI（控制反转Inversion of Control ,
 * DI:Dependency Injection 依赖注入）和AOP（面向切面编程）功能，为应用系统提供声明式的安全访问控制功能，
 * 减少了为企业系统安全控制编写大量重复代码的工作。
 *
 * 1. Spring Security Filters Chain
 * 2. AuthenticationManager, as a coordinator where you can register multiple providers,
 * 3. AuthenticationProvider, processes specific types of authentication.
 * 4. UserDetailsService, is described as a core interface that loads user
 *
 *
 */
@Configuration
@EnableWebSecurity // 打开全局的 Spring Security 功能
public class AuthConfig {

    /**
     * 4. UserDetailsService
     */
    @Bean// 将重写的 CustomUserDetailService，方法名注册到 IOC，
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }
    /**
     *  1.Spring Security Filters Chain
     * 注意： 配置允许访问的 url
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //HttpSecurity配置中一定要加上csrf().disable()，即暂时关掉跨站攻击CSRF的防御。

        return null;

//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/auth/register", "/auth/token", "/auth/validate").permitAll()
//                .and()
//                .build();

    }

    /**
     * 加密方法
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 3. AuthenticationProvider
     * 根据userDetailsService, passwordEncoder 进行 权限验证
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    /**
     * 2. AuthenticationManager
     * 获取 AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
