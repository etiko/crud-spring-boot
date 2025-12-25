package com.codewithtoyin.demo.mappers;

import com.codewithtoyin.demo.dtos.DepartmentDto;
import com.codewithtoyin.demo.dtos.RegisterDepartmentRequest;
import com.codewithtoyin.demo.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "Spring")
public interface DepartmentMapper {
    DepartmentDto departmentToDepartmentDto(Department department);
    Department departmentDtoToDepartment(RegisterDepartmentRequest request);
    void update(RegisterDepartmentRequest request, @MappingTarget Department department);
}
