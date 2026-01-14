package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.LeaveRequestResponse;
import com.codewithtoyin.demo.repositories.LeaveRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController()
public class LeaveRequestController {

    private final LeaveRequestRepository leaveRequestRepository;


    @GetMapping("leave_request")
    public ResponseEntity<List<LeaveRequestResponse>> getAllLeaveRequests() {
         var leaveRequest = leaveRequestRepository.findAll()
                .stream()
                .map(request -> new LeaveRequestResponse(
                        request.getId(),
                        request.getEmployee().getEmployeeId(),
                        request.getEmployee().getFirstName() + " " + request.getEmployee().getLastName(),
                        request.getStartDate(),
                        request.getEndDate(),
                        request.getType(),
                        request.getStatus()))
                .toList();

         return ResponseEntity.ok(leaveRequest);
    }


}
