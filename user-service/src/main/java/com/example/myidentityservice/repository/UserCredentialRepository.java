package com.example.myidentityservice.repository;

import com.example.myidentityservice.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository extends JpaRepository<UserCredential,Integer> {

    Optional<UserCredential> findByName(String name);
}
