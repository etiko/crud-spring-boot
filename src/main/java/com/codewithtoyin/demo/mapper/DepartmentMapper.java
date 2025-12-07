package com.codewithtoyin.demo.mapper;

import com.codewithtoyin.demo.dto.DepartmentDto;
import com.codewithtoyin.demo.dto.RegisterDepartmentRequest;
import com.codewithtoyin.demo.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "Spring")
public interface DepartmentMapper {
    DepartmentDto departmentToDepartmentDto(Department department);
    Department departmentDtoToDepartment(RegisterDepartmentRequest request);
    void update(RegisterDepartmentRequest request, @MappingTarget Department department);
}
