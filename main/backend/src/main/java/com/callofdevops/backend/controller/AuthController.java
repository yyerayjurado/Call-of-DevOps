package com.callofdevops.backend.controller;

import com.callofdevops.backend.dto.AuthRequest;
import com.callofdevops.backend.dto.AuthResponse;
import com.callofdevops.backend.dto.UserDTO;
import com.callofdevops.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(authService.register(userDTO));
    }
}
