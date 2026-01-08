package com.codewithtoyin.demo.mappers;
import com.codewithtoyin.demo.dtos.EmployeeRequest;
import com.codewithtoyin.demo.dtos.EmployeeResponse;
import com.codewithtoyin.demo.entities.Employee;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "departmentId", source = "department.departmentId")
    @Mapping(target = "departmentName", source = "department.departmentName")

    EmployeeResponse toResponse(Employee employee);

    Employee toEntity(EmployeeRequest request);

    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "department", ignore = true)
    void update(EmployeeRequest request, @MappingTarget Employee employee);

}
