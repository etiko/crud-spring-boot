package com.codewithtoyin.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisterEmployeeRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Long departmentId;
}
