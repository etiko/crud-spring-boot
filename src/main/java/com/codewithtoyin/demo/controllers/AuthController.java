package com.codewithtoyin.demo.controllers;

import com.codewithtoyin.demo.dtos.AuthDto;
import com.codewithtoyin.demo.dtos.JwtResponse;
import com.codewithtoyin.demo.services.JwtService;
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
    private final JwtService jwtService;


    @PostMapping
    public ResponseEntity<JwtResponse> loginUser(@Valid @RequestBody AuthDto authDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authDto.getEmail(),
                authDto.getPassword())
        );

        var token = jwtService.generateToken(authDto.getEmail());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
