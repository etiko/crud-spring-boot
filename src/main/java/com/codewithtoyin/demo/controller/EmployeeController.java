package com.codewithtoyin.demo.controller;

import com.codewithtoyin.demo.dto.EmployeeDto;
import com.codewithtoyin.demo.mapper.EmployeeMapper;
import com.codewithtoyin.demo.repositories.DepartmentRepository;
import com.codewithtoyin.demo.repositories.EmployeeRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
@Tag(name = "Employee")
public class EmployeeController {
    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @GetMapping
    public Iterable<EmployeeDto> getEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Long id) {
        var employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        var employeeDto = employeeMapper.toDto(employee);
        return ResponseEntity.ok(employeeDto);
    }

    @PostMapping()
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employeeDto,
                                                      UriComponentsBuilder uriBuilder) {

        var department = departmentRepository.findById(employeeDto.getDepartmentId()).orElse(null);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        if (employeeRepository.existsByEmail(employeeDto.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("email", "Email already exists"));
        }

        var employee = employeeMapper.toEntity(employeeDto);
        employee.setDepartment(department);

        var savedEmployee = employeeRepository.save(employee);
        employeeDto.setEmployeeId(savedEmployee.getEmployeeId());

        var uri = uriBuilder.path("/employee/{id}").buildAndExpand(employeeDto.getEmployeeId()).toUri();

        return ResponseEntity.created(uri).body(employeeDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Long id,
                                                      @RequestBody EmployeeDto employeeDto) {
        var department = departmentRepository.findById(employeeDto.getDepartmentId()).orElse(null);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }

        var employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        employeeMapper.update(employeeDto, employee);
        employee.setDepartment(department);
        var savedEmployee = employeeRepository.save(employee);

        return ResponseEntity.ok(employeeMapper.toDto(savedEmployee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        var employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        employeeRepository.delete(employee);

        return ResponseEntity.noContent().build();
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
