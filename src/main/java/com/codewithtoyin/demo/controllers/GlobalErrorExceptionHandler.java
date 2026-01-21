package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalErrorExceptionHandler {

    @ExceptionHandler(EmployeeNotFound.class)
    public ResponseEntity<Map<String, String>> handleEmployeeNotFound(EmployeeNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(DepartmentNotFound.class)
    public ResponseEntity<Map<String, String>> handleDepartmentNotFound(DepartmentNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(EmailExist.class)
    public ResponseEntity<Map<String, String>> handleEmailExist(EmailExist ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(LeaveNotFound.class)
    public ResponseEntity<Map<String, String>> handleLeaveNotFound(LeaveNotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(InvalidRequestState.class)
    public ResponseEntity<Map<String, String>> handleInvalidLeaveRequest(InvalidRequestState ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "error", "INVALID_REQUEST_STATE",
                "message", ex.getMessage()));
    }
}
