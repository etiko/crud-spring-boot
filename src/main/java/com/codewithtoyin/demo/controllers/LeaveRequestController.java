package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.LeaveRequestRequest;
import com.codewithtoyin.demo.dtos.LeaveRequestResponse;
import com.codewithtoyin.demo.entities.LeaveRequest;
import com.codewithtoyin.demo.enums.LeaveStatus;
import com.codewithtoyin.demo.mappers.LeaveRequestMapper;
import com.codewithtoyin.demo.repositories.EmployeeRepository;
import com.codewithtoyin.demo.repositories.LeaveRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController()
@RequestMapping("/leave_request")
public class LeaveRequestController {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;
    private final LeaveRequestMapper leaveRequestMapper;


    @GetMapping()
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

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestResponse> getLeaveRequestsById(@PathVariable Long id) {
        var foundLeaveRequest = leaveRequestRepository.findById(id).orElse(null);

        if (foundLeaveRequest == null) {
            ResponseEntity.notFound().build();
        }

        var leaveResponse = leaveRequestMapper.toResponse(foundLeaveRequest);

//        var leaveRequestResponse = new LeaveRequestResponse();
//        leaveRequestResponse.setId(foundLeaveRequest.getId());
//        leaveRequestResponse.setEmployeeId(foundLeaveRequest.getEmployee().getEmployeeId());
//        leaveRequestResponse.setEmployeeName(foundLeaveRequest.getEmployee().getFirstName() + " "
//                + foundLeaveRequest.getEmployee().getLastName());
//        leaveRequestResponse.setStartDate(foundLeaveRequest.getStartDate());
//        leaveRequestResponse.setEndDate(foundLeaveRequest.getEndDate());
//        leaveRequestResponse.setType(foundLeaveRequest.getType());
//        leaveRequestResponse.setStatus(foundLeaveRequest.getStatus());

        return ResponseEntity.ok(leaveResponse);
    }

    @PostMapping("/{id}")
    public ResponseEntity<LeaveRequestResponse> submitLeaveRequest(
            @PathVariable Long id, @RequestBody LeaveRequestRequest request) {
        var employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

//      TODO: need to find a unique param to prevent duplicate or overlapping entries.

        var leaveRequestEntity = leaveRequestMapper.toEntity(request);
        leaveRequestEntity.setEmployee(employee);
        leaveRequestEntity.setStatus(LeaveStatus.PENDING);

//        Manually creating instance.
//        var leaveRequestEntity = new LeaveRequest();
//        leaveRequestEntity.setEmployee(employee);
//        leaveRequestEntity.setStartDate(request.getStartDate());
//        leaveRequestEntity.setEndDate(request.getEndDate());
//        leaveRequestEntity.setType(request.getLeaveType());
//        leaveRequestEntity.setStatus(LeaveStatus.PENDING);

        var savedLeaveRequest = leaveRequestRepository.save(leaveRequestEntity);
        var leaveResponse = leaveRequestMapper.toResponse(savedLeaveRequest);

//        var response = new LeaveRequestResponse();
//
//        response.setId(savedLeaveRequest.getId());
//        response.setEmployeeId(savedLeaveRequest.getEmployee().getEmployeeId());
//        response.setEmployeeName(savedLeaveRequest.getEmployee().getFirstName()
//                + " " + savedLeaveRequest.getEmployee().getLastName());
//        response.setStartDate(savedLeaveRequest.getStartDate());
//        response.setEndDate(savedLeaveRequest.getEndDate());
//        response.setLeaveType(savedLeaveRequest.getType());
//        response.setLeaveStatus(savedLeaveRequest.getStatus());

        return ResponseEntity.ok(leaveResponse);
    }


}
