package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.DepartmentResponse;
import com.codewithtoyin.demo.dtos.DepartmentRequest;
import com.codewithtoyin.demo.services.DepartmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentServiceImpl departmentServiceImpl;

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getDepartments() {
        return ResponseEntity.ok(departmentServiceImpl.getDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentServiceImpl.getDepartmentById(id));
    }

    @PostMapping()
    public ResponseEntity<DepartmentResponse> createDepartment(@RequestBody DepartmentRequest request,
                                                               UriComponentsBuilder uriBuilder) {
        var createdDepartment = departmentServiceImpl.createDepartment(request);

        var uri = uriBuilder.path("department/{id}")
                .buildAndExpand(createdDepartment.getDepartmentId()).toUri();

      return ResponseEntity.created(uri).body(createdDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(@PathVariable Long id,
                                                               @RequestBody DepartmentRequest request) {
       return ResponseEntity.ok(departmentServiceImpl.updateDepartment(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartmentById(@PathVariable Long id) {
        departmentServiceImpl.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
