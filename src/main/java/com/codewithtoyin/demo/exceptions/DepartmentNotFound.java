package com.codewithtoyin.demo.exceptions;

public class DepartmentNotFound extends RuntimeException {
    public DepartmentNotFound(String message) {
        super(message);
    }
}
