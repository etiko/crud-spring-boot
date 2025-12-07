package com.codewithtoyin.demo.controller;

import com.codewithtoyin.demo.dto.DepartmentDto;
import com.codewithtoyin.demo.dto.RegisterDepartmentRequest;
import com.codewithtoyin.demo.mapper.DepartmentMapper;
import com.codewithtoyin.demo.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
                .map(departmentMapper::departmentToDepartmentDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(departmentMapper.departmentToDepartmentDto(department));
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody RegisterDepartmentRequest request) {
        var department = departmentMapper.departmentDtoToDepartment(request);

        departmentRepository.save(department);

        return ResponseEntity.ok(departmentMapper.departmentToDepartmentDto(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable Long id, @RequestBody RegisterDepartmentRequest request) {
        var department = departmentRepository.findById(id).orElse(null);
        if (department == null) {
            ResponseEntity.notFound().build();
        }
        departmentMapper.update(request, department);
        departmentRepository.save(department);

       return ResponseEntity.ok(departmentMapper.departmentToDepartmentDto(department));
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
