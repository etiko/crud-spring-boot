package com.codewithtoyin.demo.repositories;

import com.codewithtoyin.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
