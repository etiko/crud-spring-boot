package com.codewithtoyin.demo.entities;

import com.codewithtoyin.demo.enums.LeaveStatus;
import com.codewithtoyin.demo.enums.LeaveType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "leave_requests")
@ToString
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private LeaveType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @Column(name = "decided_by")
    private Long decidedBy;

    @Column(name = "decided_at")
    private LocalDate decidedAt;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

}