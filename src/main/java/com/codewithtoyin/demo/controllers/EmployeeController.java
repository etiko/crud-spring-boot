package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.EmployeeRequest;
import com.codewithtoyin.demo.dtos.EmployeeResponse;
import com.codewithtoyin.demo.exceptions.DepartmentNotFound;
import com.codewithtoyin.demo.exceptions.EmailExist;
import com.codewithtoyin.demo.exceptions.EmployeeNotFound;
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
        var uri = uriBuilder.path("/employee/{id}").buildAndExpand(createdEmployee.getEmployeeId()).toUri();

        return ResponseEntity.created(uri).body(createdEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable Long id,
                                                          @Valid @RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<Map<String, String>> handleEmployeeNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Employee not found"));
    }

    @ExceptionHandler(DepartmentNotFound.class)
    public ResponseEntity<Map<String, String>> handleDepartmentNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Department not found"));
    }

    @ExceptionHandler(EmailExist.class)
    public ResponseEntity<Map<String, String>> handleEmailExist() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Email already exist"));
    }

    //       var employeeDto = new EmployeeDto(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(),
//               employee.getDepartment() == null ? null : employee.getDepartment().getDepartmentId());
//       return ResponseEntity.ok(employeeMapper::toDto);
//}

//        // 1. Load department from DB
//        Department department = departmentRepository.findById(request.getDepartmentId())
//                .orElseThrow(() -> new RuntimeException("Department not found"));
//
//        // 2. Create Employee entity
//        Employee employee = new Employee();
//        employee.setFirstName(request.getFirstName());
//        employee.setLastName(request.getLastName());
//        employee.setEmail(request.getEmail());
//        employee.setAddress(request.getAddress());
//        employee.setDepartment(department);
//
//        // 3. Save
//        Employee saved = employeeRepository.save(employee);
//
//        // 4. Return response dto (you can customise)
//        return new EmployeeDto(
//                saved.getEmployeeId(),
//                saved.getFirstName(),
//                saved.getLastName(),
//                department.getDepartmentId()
//        );


}
