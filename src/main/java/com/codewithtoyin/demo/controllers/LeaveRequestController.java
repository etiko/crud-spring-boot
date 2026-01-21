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
            @PathVariable Long id, @RequestBody LeaveRequestRequest request,
            UriComponentsBuilder uriBuilder) {
        var createdLeaveRequest = leaveRequestService.submitLeaveRequest(id, request);
        var uri = uriBuilder.path("/leave_request/{id}").buildAndExpand(createdLeaveRequest.getId()).toUri();

        return ResponseEntity.created(uri).body(createdLeaveRequest);
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
