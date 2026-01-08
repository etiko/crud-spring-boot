package com.codewithtoyin.demo.services;

import com.codewithtoyin.demo.dtos.DepartmentResponse;
import com.codewithtoyin.demo.dtos.DepartmentRequest;
import com.codewithtoyin.demo.exceptions.DepartmentNotFound;
import com.codewithtoyin.demo.mappers.DepartmentMapper;
import com.codewithtoyin.demo.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {

    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    public List<DepartmentResponse> getDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    public DepartmentResponse getDepartmentById(Long id) {
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new DepartmentNotFound();
        }
        return departmentMapper.toResponse(department);
    }

    public DepartmentResponse createDepartment(DepartmentRequest request) {
        var department = departmentMapper.toEntity(request);
        var savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }

    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new DepartmentNotFound();
        }
        departmentMapper.update(request, department);
        var savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }

    public void deleteDepartment(Long id) {
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new DepartmentNotFound();
        }
        departmentRepository.delete(department);
    }

}
