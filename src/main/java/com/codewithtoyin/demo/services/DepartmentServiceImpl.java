package com.codewithtoyin.demo.services;

import com.codewithtoyin.demo.dtos.DepartmentRequest;
import com.codewithtoyin.demo.dtos.DepartmentResponse;
import com.codewithtoyin.demo.entities.Department;
import com.codewithtoyin.demo.exceptions.DepartmentNotFound;
import com.codewithtoyin.demo.mappers.DepartmentMapper;
import com.codewithtoyin.demo.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .toList();
    }

    @Override
    public DepartmentResponse getDepartmentById(Long id) {
        return departmentMapper.toResponse(findDepartment(id));
    }

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        var department = departmentMapper.toEntity(request);

        department.setCreatedAt(LocalDateTime.now());

        var savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }

    @Override
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        var department = findDepartment(id);

        departmentMapper.update(request, department);

        var savedDepartment = departmentRepository.save(department);

        return departmentMapper.toResponse(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.delete(findDepartment(id));
    }

    private  Department findDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFound("Department " + id + " not found"));
    }

}
