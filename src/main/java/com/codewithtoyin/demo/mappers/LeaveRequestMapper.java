package com.codewithtoyin.demo.mappers;

import com.codewithtoyin.demo.dtos.LeaveRequestRequest;
import com.codewithtoyin.demo.dtos.LeaveRequestResponse;
import com.codewithtoyin.demo.entities.LeaveRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface LeaveRequestMapper {
    @Mapping(target = "type", source = "type")
    LeaveRequest toEntity(LeaveRequestRequest request);

    @Mapping(target = "employeeId", source = "employee.employeeId")
    @Mapping(target = "employeeName", source = ".", qualifiedByName = "getFullName")
    LeaveRequestResponse toResponse(LeaveRequest leaveRequest);

    @Named("getFullName")
    default String getFullName(LeaveRequest leaveRequest) {
        return leaveRequest.getEmployee().getFirstName() +  " " + leaveRequest.getEmployee().getLastName();
    }
}
