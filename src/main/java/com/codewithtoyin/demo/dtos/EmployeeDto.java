package com.codewithtoyin.demo.dtos;

import com.codewithtoyin.demo.validator.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class EmployeeDto {
    private Long employeeId;
    @NotBlank(message = "First name is required")
    @Size(max = 255, message = "Must be less than 255 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Must be less than 255 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Valid email is required")
    @Lowercase(message = "Email must be in lowercase")
    private String email;
    private String address;
    private Long departmentId;
}
