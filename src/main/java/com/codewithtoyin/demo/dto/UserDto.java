package com.codewithtoyin.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UserDto {
    private String name;
    private String email;
    private String password;
}
