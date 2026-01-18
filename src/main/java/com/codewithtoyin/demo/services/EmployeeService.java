package com.codewithtoyin.demo.services;

import com.codewithtoyin.demo.dtos.EmployeeRequest;
import com.codewithtoyin.demo.dtos.EmployeeResponse;
import com.codewithtoyin.demo.enums.Role;
import com.codewithtoyin.demo.exceptions.DepartmentNotFound;
import com.codewithtoyin.demo.exceptions.EmailExist;
import com.codewithtoyin.demo.exceptions.EmployeeNotFound;
import com.codewithtoyin.demo.mappers.EmployeeMapper;
import com.codewithtoyin.demo.repositories.DepartmentRepository;
import com.codewithtoyin.demo.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    public EmployeeResponse getEmployee(Long employeeId) {
        var employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new EmployeeNotFound();
        }

        return employeeMapper.toResponse(employee);
    }

    public EmployeeResponse createEmployee(EmployeeRequest request) {
        var department = departmentRepository.findById(request.getDepartmentId()).orElse(null);
        if (department == null) {
            throw new DepartmentNotFound();
        }
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new EmailExist();
        }

        var employee = employeeMapper.toEntity(request);
        employee.setDepartment(department);
        employee.setStartDate(LocalDate.now());

        var savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toResponse(savedEmployee);
    }

    public EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest request) {
        var department = departmentRepository.findById(request.getDepartmentId()).orElse(null);
        if (department == null) {
            throw new DepartmentNotFound();
        }

        var employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new EmployeeNotFound();
        }

        employeeMapper.update(request, employee);
        employee.setDepartment(department);
        var savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    public EmployeeResponse updateEmployeeRole(Long employeeId) {
        var employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new EmployeeNotFound();
        }

        employee.setRole(Role.MANAGER);
        var savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toResponse(savedEmployee);
    }

    public void deleteEmployee(Long employeeId) {
        var employee = employeeRepository.findById(employeeId).orElse(null);
        if (employee == null) {
            throw new EmployeeNotFound();
        }
        employeeRepository.delete(employee);
    }
}
