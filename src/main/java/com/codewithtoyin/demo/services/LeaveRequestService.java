package com.codewithtoyin.demo.services;

import com.codewithtoyin.demo.dtos.LeaveRequestRequest;
import com.codewithtoyin.demo.dtos.LeaveRequestResponse;
import com.codewithtoyin.demo.entities.LeaveRequest;
import com.codewithtoyin.demo.enums.LeaveStatus;
import com.codewithtoyin.demo.exceptions.EmployeeNotFound;
import com.codewithtoyin.demo.exceptions.InvalidRequestState;
import com.codewithtoyin.demo.exceptions.LeaveNotFound;
import com.codewithtoyin.demo.mappers.LeaveRequestMapper;
import com.codewithtoyin.demo.repositories.EmployeeRepository;
import com.codewithtoyin.demo.repositories.LeaveRequestRepository;
import com.sun.jdi.request.InvalidRequestStateException;
import lombok.AllArgsConstructor;
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
        var foundLeaveRequest = getLeaveRequest(id);

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

    public LeaveRequestResponse approveLeaveRequest(Long id) {
        var leaveRequest = getLeaveRequest(id);
        assertPending(leaveRequest, "approve");

        leaveRequest.setStatus(LeaveStatus.APPROVED);
        var savedLeaveRequest = leaveRequestRepository.save(leaveRequest);

        return leaveRequestMapper.toResponse(savedLeaveRequest);
    }

    public LeaveRequestResponse rejectLeaveRequest(Long id) {
        var leaveRequest = getLeaveRequest(id);
        assertPending(leaveRequest, "reject");

        leaveRequest.setStatus(LeaveStatus.REJECTED);
        var savedLeaveRequest = leaveRequestRepository.save(leaveRequest);

        return leaveRequestMapper.toResponse(savedLeaveRequest);
    }

    public LeaveRequestResponse cancelLeaveRequest(Long id) {
        var leaveRequest = getLeaveRequest(id);
        assertPending(leaveRequest, "cancel");

        leaveRequest.setStatus(LeaveStatus.CANCELLED);
        var savedLeaveRequest = leaveRequestRepository.save(leaveRequest);

        return leaveRequestMapper.toResponse(savedLeaveRequest);
    }

    private static void assertPending(LeaveRequest leaveRequest, String action) {
        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {
           throw new InvalidRequestState("Cannot " + action + " a leave request in status " +
                   leaveRequest.getStatus());
        }
    }

    private LeaveRequest getLeaveRequest(Long id) {
        return leaveRequestRepository.findById(id).orElseThrow(
                () -> new LeaveNotFound("Leave request " + id + " not found"));
    }


}
