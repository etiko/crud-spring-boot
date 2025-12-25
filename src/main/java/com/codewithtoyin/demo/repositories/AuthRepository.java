package com.codewithtoyin.demo.repositories;

import com.codewithtoyin.demo.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, Long> {
    boolean existsByEmail(String email);
}
