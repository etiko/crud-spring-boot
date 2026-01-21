package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.EmployeeRequest;
import com.codewithtoyin.demo.dtos.EmployeeResponse;
import com.codewithtoyin.demo.enums.Role;
import com.codewithtoyin.demo.exceptions.DepartmentNotFound;
import com.codewithtoyin.demo.exceptions.EmailExist;
import com.codewithtoyin.demo.exceptions.EmployeeNotFound;
import com.codewithtoyin.demo.mappers.EmployeeMapper;
import com.codewithtoyin.demo.repositories.EmployeeRepository;
import com.codewithtoyin.demo.services.EmployeeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@Tag(name = "Employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @PostMapping()
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request,
                                                           UriComponentsBuilder uriBuilder) {
        var createdEmployee = employeeService.createEmployee(request);
        var uri = uriBuilder.path("/employee/{id}")
                .buildAndExpand(createdEmployee.getEmployeeId())
                .toUri();

        return ResponseEntity.created(uri).body(createdEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id,
                                                          @Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<EmployeeResponse> updateEmployeeRole(@PathVariable Long id) {
       return ResponseEntity.ok(employeeService.updateEmployeeRole(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
