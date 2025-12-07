package com.codewithtoyin.demo.repositories;

import com.codewithtoyin.demo.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
