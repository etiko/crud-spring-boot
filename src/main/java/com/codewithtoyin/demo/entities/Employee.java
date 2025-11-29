package com.codewithtoyin.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Employee {
    Long employeeId;
    String firtName;
    String lastName;
    String email;
}
