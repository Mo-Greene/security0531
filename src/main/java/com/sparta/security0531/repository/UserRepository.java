package com.sparta.security0531.repository;

import com.sparta.security0531.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    //username 중복확인
    boolean existsByUsername(String username);
}