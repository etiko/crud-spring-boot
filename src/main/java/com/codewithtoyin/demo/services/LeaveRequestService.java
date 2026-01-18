package com.codewithtoyin.demo.services;

import com.codewithtoyin.demo.dtos.LeaveRequestRequest;
import com.codewithtoyin.demo.dtos.LeaveRequestResponse;
import com.codewithtoyin.demo.enums.LeaveStatus;
import com.codewithtoyin.demo.exceptions.EmployeeNotFound;
import com.codewithtoyin.demo.exceptions.LeaveNotFound;
import com.codewithtoyin.demo.mappers.LeaveRequestMapper;
import com.codewithtoyin.demo.repositories.EmployeeRepository;
import com.codewithtoyin.demo.repositories.LeaveRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveRequestMapper leaveRequestMapper;
    private final EmployeeRepository employeeRepository;

    public List<LeaveRequestResponse> getAllLeaveRequests() {
        return leaveRequestRepository.findAll()
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
    }

    public LeaveRequestResponse getLeaveRequestById(Long id) {
        var foundLeaveRequest = leaveRequestRepository.findById(id).orElse(null);

        if (foundLeaveRequest == null) {
            throw new LeaveNotFound();
//            ResponseEntity.notFound().build();
        }

        return leaveRequestMapper.toResponse(foundLeaveRequest);

//        var leaveRequestResponse = new LeaveRequestResponse();
//        leaveRequestResponse.setId(foundLeaveRequest.getId());
//        leaveRequestResponse.setEmployeeId(foundLeaveRequest.getEmployee().getEmployeeId());
//        leaveRequestResponse.setEmployeeName(foundLeaveRequest.getEmployee().getFirstName() + " "
//                + foundLeaveRequest.getEmployee().getLastName());
//        leaveRequestResponse.setStartDate(foundLeaveRequest.getStartDate());
//        leaveRequestResponse.setEndDate(foundLeaveRequest.getEndDate());
//        leaveRequestResponse.setType(foundLeaveRequest.getType());
//        leaveRequestResponse.setStatus(foundLeaveRequest.getStatus());

    }

    public LeaveRequestResponse submitLeaveRequest(Long id, LeaveRequestRequest request) {
        var employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new EmployeeNotFound();
        }

//      TODO: need to find a unique param to prevent duplicate or overlapping entries.

        var leaveRequestEntity = leaveRequestMapper.toEntity(request);
        leaveRequestEntity.setEmployee(employee);
        leaveRequestEntity.setStatus(LeaveStatus.PENDING);

        var savedLeaveRequest = leaveRequestRepository.save(leaveRequestEntity);
        return leaveRequestMapper.toResponse(savedLeaveRequest);

        //        Manually creating instance.
//        var leaveRequestEntity = new LeaveRequest();
//        leaveRequestEntity.setEmployee(employee);
//        leaveRequestEntity.setStartDate(request.getStartDate());
//        leaveRequestEntity.setEndDate(request.getEndDate());
//        leaveRequestEntity.setType(request.getLeaveType());
//        leaveRequestEntity.setStatus(LeaveStatus.PENDING);

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

    }


}
