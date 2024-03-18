package com.example.myidentityservice.config;

import com.example.myidentityservice.entity.UserCredential;
import com.example.myidentityservice.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    /**
     * 从数据库查用户名，密码
     *
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> userCredential  = userCredentialRepository.findByName(username);
        //return userCredential.map(userCredential-> new CustomUserDetails(userCredential));
       // public <U> Optional<U> map(Function<? super T, ? extends U> mapper)
        return userCredential.map(CustomUserDetails::new)
                .orElseThrow(()->new UsernameNotFoundException("User not found :"+ username));
    }
}
