package com.codewithtoyin.demo.mapper;
import com.codewithtoyin.demo.dto.EmployeeDto;
import com.codewithtoyin.demo.dto.RegisterEmployeeRequest;
import com.codewithtoyin.demo.dto.UpdateEmployeeRequest;
import com.codewithtoyin.demo.entities.Department;
import com.codewithtoyin.demo.entities.Employee;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(target = "departmentId", source = "department.departmentId")
    EmployeeDto toDto(Employee employee);

    Employee toEntity(EmployeeDto employeeDto);

    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "department", ignore = true)
    void update(EmployeeDto employeeDto, @MappingTarget Employee employee);


}
