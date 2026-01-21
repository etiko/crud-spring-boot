package com.codewithtoyin.demo.exceptions;

public class EmailExist extends RuntimeException {
    public EmailExist(String message) {
        super(message);
    }
}
