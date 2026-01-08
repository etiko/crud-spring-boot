package com.codewithtoyin.demo.mappers;

import com.codewithtoyin.demo.dtos.DepartmentDto;
import com.codewithtoyin.demo.dtos.RegisterDepartmentRequest;
import com.codewithtoyin.demo.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);
    Department toEntity(RegisterDepartmentRequest request);
    void update(RegisterDepartmentRequest request, @MappingTarget Department department);
}
