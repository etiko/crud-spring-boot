package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.AuthDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth/login")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;


    @PostMapping
    public ResponseEntity<?> loginUser(@Valid @RequestBody AuthDto authDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authDto.getEmail(),
                authDto.getPassword())
        );

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
