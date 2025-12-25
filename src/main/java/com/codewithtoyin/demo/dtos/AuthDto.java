package com.codewithtoyin.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthDto {
    @NotBlank(message = "Email cannot be blank")
    private String email;
    @NotBlank(message = "password cannot be empty")
    private String password;
}
