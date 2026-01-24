package com.codewithtoyin.demo.services;

import com.codewithtoyin.demo.dtos.DepartmentRequest;
import com.codewithtoyin.demo.dtos.DepartmentResponse;
import com.codewithtoyin.demo.entities.Department;
import com.codewithtoyin.demo.exceptions.DepartmentNotFound;

import java.util.List;

public interface DepartmentService {
    List<DepartmentResponse> getDepartments();

    DepartmentResponse getDepartmentById(Long id);

    DepartmentResponse createDepartment(DepartmentRequest request);

    DepartmentResponse updateDepartment(Long id, DepartmentRequest request);

    void deleteDepartment(Long id);
}
