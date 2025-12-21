package com.codewithtoyin.demo.controller;

import com.codewithtoyin.demo.dto.UserDto;
import com.codewithtoyin.demo.mapper.UserMapper;
import com.codewithtoyin.demo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        var user = userMapper.userDtoToUser(userDto);

        var savedUser = userRepository.save(user);

        return ResponseEntity.ok().body(userMapper.userToUserDto(savedUser));
    }
}
