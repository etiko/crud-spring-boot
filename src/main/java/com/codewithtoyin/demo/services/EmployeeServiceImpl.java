package com.codewithtoyin.demo.services;

import com.codewithtoyin.demo.dtos.EmployeeRequest;
import com.codewithtoyin.demo.dtos.EmployeeResponse;
import com.codewithtoyin.demo.entities.Department;
import com.codewithtoyin.demo.entities.Employee;
import com.codewithtoyin.demo.enums.Role;
import com.codewithtoyin.demo.exceptions.DepartmentNotFound;
import com.codewithtoyin.demo.exceptions.EmailExist;
import com.codewithtoyin.demo.exceptions.EmployeeNotFound;
import com.codewithtoyin.demo.mappers.EmployeeMapper;
import com.codewithtoyin.demo.repositories.DepartmentRepository;
import com.codewithtoyin.demo.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponse> getEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toResponse)
                .toList();
    }

    @Override
    public EmployeeResponse getEmployee(Long employeeId) {
        var employee =findEmployee(employeeId);

        return employeeMapper.toResponse(employee);
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        var department = findDepartment(request.getDepartmentId());

        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new EmailExist("Email address " + request.getEmail() + " exist. Try another");
        }

        var employee = employeeMapper.toEntity(request);

        employee.setDepartment(department);
        employee.setStartDate(LocalDate.now());

        var savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long employeeId, EmployeeRequest request) {
        var department = findDepartment(request.getDepartmentId());
        var employee = findEmployee(employeeId);

        employeeMapper.update(request, employee);
        employee.setDepartment(department);

        var savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse updateEmployeeRole(Long employeeId) {
        var employee = findEmployee(employeeId);

        employee.setRole(Role.MANAGER);

        var savedEmployee = employeeRepository.save(employee);

        return employeeMapper.toResponse(savedEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.delete(findEmployee(employeeId));
    }

    private Employee findEmployee(Long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFound("Employee " + employeeId + " not found"));
    }

    private Department findDepartment(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFound("Department " + id + " not found"));
    }
}
