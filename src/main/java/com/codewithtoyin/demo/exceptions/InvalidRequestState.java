package com.codewithtoyin.demo.exceptions;

public class InvalidRequestState extends RuntimeException {

    public InvalidRequestState(String message) {
        super(message);
    }
}
