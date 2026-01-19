package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.LeaveRequestRequest;
import com.codewithtoyin.demo.dtos.LeaveRequestResponse;
import com.codewithtoyin.demo.exceptions.LeaveNotFound;
import com.codewithtoyin.demo.services.LeaveRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController()
@RequestMapping("/leave_request")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @GetMapping()
    public ResponseEntity<List<LeaveRequestResponse>> getAllLeaveRequests() {
         return ResponseEntity.ok(leaveRequestService.getAllLeaveRequests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestResponse> getLeaveRequestsById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.getLeaveRequestById(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<LeaveRequestResponse> submitLeaveRequest(
            @PathVariable Long id, @RequestBody LeaveRequestRequest request) {
        return ResponseEntity.ok(leaveRequestService.submitLeaveRequest(id, request));
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<LeaveRequestResponse> approveLeaveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.approveLeaveRequest(id));
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<LeaveRequestResponse> rejectLeaveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.rejectLeaveRequest(id));
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<LeaveRequestResponse> cancelLeaveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(leaveRequestService.cancelLeaveRequest(id));
    }


}
