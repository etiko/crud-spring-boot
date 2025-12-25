package com.codewithtoyin.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateEmployeeRequest {
    private String firstName;
    private String lastName;
    private Long departmentId;
}
