package com.ahmed.backend.controller;

import com.ahmed.backend.payload.request.LoginRequest;
import com.ahmed.backend.payload.request.RegisterRequest;
import com.ahmed.backend.payload.response.JwtResponse;
import com.ahmed.backend.payload.response.MessageResponse;
import com.ahmed.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        MessageResponse response = authService.register(registerRequest);
        if (response.getMessage().startsWith("Error")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticate(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }
}

