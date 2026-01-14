package com.codewithtoyin.demo.dtos;

import com.codewithtoyin.demo.entities.Employee;
import com.codewithtoyin.demo.enums.LeaveStatus;
import com.codewithtoyin.demo.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LeaveRequestResponse {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType leaveType;
    private LeaveStatus leaveStatus;
}
