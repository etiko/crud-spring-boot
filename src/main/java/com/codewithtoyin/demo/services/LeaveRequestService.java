package com.codewithtoyin.demo.services;

import com.codewithtoyin.demo.dtos.LeaveRequestRequest;
import com.codewithtoyin.demo.dtos.LeaveRequestResponse;

import java.util.List;

public interface LeaveRequestService {
    List<LeaveRequestResponse> getAllLeaveRequests();

    LeaveRequestResponse getLeaveRequestById(Long id);

    LeaveRequestResponse submitLeaveRequest(Long id, LeaveRequestRequest request);

    LeaveRequestResponse approveLeaveRequest(Long id);

    LeaveRequestResponse rejectLeaveRequest(Long id);

    LeaveRequestResponse cancelLeaveRequest(Long id);
}
