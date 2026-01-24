package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.EmployeeRequest;
import com.codewithtoyin.demo.dtos.EmployeeResponse;
import com.codewithtoyin.demo.services.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@Tag(name = "Employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeServiceImpl;

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getEmployees() {
        return ResponseEntity.ok(employeeServiceImpl.getEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeServiceImpl.getEmployee(id));
    }

    @PostMapping()
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request,
                                                           UriComponentsBuilder uriBuilder) {
        var createdEmployee = employeeServiceImpl.createEmployee(request);
        var uri = uriBuilder.path("/employee/{id}")
                .buildAndExpand(createdEmployee.getEmployeeId())
                .toUri();

        return ResponseEntity.created(uri).body(createdEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id,
                                                          @Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeServiceImpl.updateEmployee(id, request));
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<EmployeeResponse> updateEmployeeRole(@PathVariable Long id) {
       return ResponseEntity.ok(employeeServiceImpl.updateEmployeeRole(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeServiceImpl.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
