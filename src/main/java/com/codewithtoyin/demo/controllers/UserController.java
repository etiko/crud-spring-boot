package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.UserDto;
import com.codewithtoyin.demo.mappers.UserMapper;
import com.codewithtoyin.demo.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@RequestMapping()
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
           return ResponseEntity.badRequest().body(Map.of("email", "Email already exists"));
        }

        var user = userMapper.userDtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

        return ResponseEntity.ok().body(null);
    }

}
