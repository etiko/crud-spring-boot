package com.codewithtoyin.demo.dtos;

import com.codewithtoyin.demo.enums.LeaveStatus;
import com.codewithtoyin.demo.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class LeaveRequestRequest {
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType leaveType;
    private LeaveStatus leaveStatus;
}
