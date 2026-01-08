package com.codewithtoyin.demo.services;

import com.codewithtoyin.demo.dtos.DepartmentDto;
import com.codewithtoyin.demo.dtos.RegisterDepartmentRequest;
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

    public List<DepartmentDto> getDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDto)
                .toList();
    }

    public DepartmentDto getDepartmentById(Long id) {
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new DepartmentNotFound();
        }
        return departmentMapper.toDto(department);
    }

    public DepartmentDto createDepartment(RegisterDepartmentRequest request) {
        var department = departmentMapper.toEntity(request);
        var savedDepartment = departmentRepository.save(department);

        return departmentMapper.toDto(savedDepartment);
    }

    public DepartmentDto updateDepartment(Long id, RegisterDepartmentRequest request) {
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new DepartmentNotFound();
        }
        departmentMapper.update(request, department);
        var savedDepartment = departmentRepository.save(department);

        return departmentMapper.toDto(savedDepartment);
    }

    public void deleteDepartment(Long id) {
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            throw new DepartmentNotFound();
        }
        departmentRepository.delete(department);
    }

}
