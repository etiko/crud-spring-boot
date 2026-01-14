package com.codewithtoyin.demo.dtos;

import com.codewithtoyin.demo.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DepartmentResponse {
    private Long departmentId;
    private String departmentName;
    private Status status;
    private LocalDateTime createdAt;
}
