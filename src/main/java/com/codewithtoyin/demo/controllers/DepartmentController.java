package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.DepartmentDto;
import com.codewithtoyin.demo.dtos.RegisterDepartmentRequest;
import com.codewithtoyin.demo.mappers.DepartmentMapper;
import com.codewithtoyin.demo.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {
    private DepartmentRepository departmentRepository;
    private DepartmentMapper departmentMapper;

    @GetMapping
    public Iterable<DepartmentDto> getDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(departmentMapper.toDto(department));
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody RegisterDepartmentRequest request) {
        var department = departmentMapper.toEntity(request);

        var savedDepartment = departmentRepository.save(department);

        return ResponseEntity.ok(departmentMapper.toDto(savedDepartment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id,
                                                          @RequestBody RegisterDepartmentRequest request) {
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            ResponseEntity.notFound().build();
        }
        departmentMapper.update(request, department);
        var savedDepartment = departmentRepository.save(department);

       return ResponseEntity.ok(departmentMapper.toDto(savedDepartment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable Long id) {
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            ResponseEntity.notFound().build();
        }
        departmentRepository.delete(department);
        return ResponseEntity.noContent().build();
    }
}
