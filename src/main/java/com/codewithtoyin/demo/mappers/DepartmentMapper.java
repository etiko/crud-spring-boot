package com.codewithtoyin.demo.mappers;

import com.codewithtoyin.demo.dtos.DepartmentResponse;
import com.codewithtoyin.demo.dtos.DepartmentRequest;
import com.codewithtoyin.demo.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentResponse toResponse(Department department);
    Department toEntity(DepartmentRequest request);
    void update(DepartmentRequest request, @MappingTarget Department department);
}
