package com.codewithtoyin.demo.repositories;

import com.codewithtoyin.demo.entities.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
}