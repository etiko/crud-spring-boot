package com.codewithtoyin.demo.entities;

import com.codewithtoyin.demo.enums.Roles;
import com.codewithtoyin.demo.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
@ToString
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Roles role = Roles.EMPLOYEE;

    @Column(name = "start_date")
    private LocalDate startDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;
}
