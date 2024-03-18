package com.example.myidentityservice.config;

import com.example.myidentityservice.entity.UserCredential;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 实现  UserDetails 接口
 * 该接口十分重要，用于从数据库中验证用户名密码
 * 用户实体类需要实现 ”UserDetails“ 接口，这个接口要求实现 getUsername、getPassword、getAuthorities
 * 三个方法，用以获取用户名、密码和权限，以及 isAccountNonExpired`isAccountNonLocked、isCredentialsNonExpired、
 * isEnabled 这四个判断是否是有效用户的方法
 */
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;

    // UserCredential map to CustomUserDetails 使用
    public CustomUserDetails(UserCredential userCredential) {
        this.username = userCredential.getName();
        this.password = userCredential.getPassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
