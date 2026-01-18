package com.codewithtoyin.demo.dtos;

import com.codewithtoyin.demo.enums.Role;
import com.codewithtoyin.demo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EmployeeResponse {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;

    private String jobTitle;
    private Status status;
    private Role role;

    private LocalDate startDate;
    private LocalDate createdAt;

    private Long departmentId;
    private String departmentName;
}
