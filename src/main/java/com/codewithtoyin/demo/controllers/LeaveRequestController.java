package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.LeaveRequestRequest;
import com.codewithtoyin.demo.dtos.LeaveRequestResponse;
import com.codewithtoyin.demo.services.LeaveRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController()
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @GetMapping("/leave_request")
    public ResponseEntity<List<LeaveRequestResponse>> getAllLeaveRequests() {
         return ResponseEntity.ok(leaveRequestService.getAllLeaveRequests());
    }

    @GetMapping("/leave_request/{id}")
    public ResponseEntity<LeaveRequestResponse> getLeaveRequestsById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.getLeaveRequestById(id));
    }

    @PostMapping("employees/{id}/leave_request")
    public ResponseEntity<LeaveRequestResponse> submitLeaveRequest(
            @PathVariable Long id, @RequestBody LeaveRequestRequest request,
            UriComponentsBuilder uriBuilder) {
        var createdLeaveRequest = leaveRequestService.submitLeaveRequest(id, request);
        var uri = uriBuilder.path("employees/leave_request/{id}").buildAndExpand(createdLeaveRequest.getId()).toUri();

        return ResponseEntity.created(uri).body(createdLeaveRequest);
    }

    @PostMapping("/leave_request/{id}/approve")
    public ResponseEntity<LeaveRequestResponse> approveLeaveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.approveLeaveRequest(id));
    }

    @PostMapping("/leave_request/{id}/reject")
    public ResponseEntity<LeaveRequestResponse> rejectLeaveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.rejectLeaveRequest(id));
    }

    @PostMapping("/leave_request/{id}/cancel")
    public ResponseEntity<LeaveRequestResponse> cancelLeaveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.cancelLeaveRequest(id));
    }


}
