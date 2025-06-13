package com.example.callerapp.controller;

import com.example.callerapp.model.AuthRequest;
import com.example.callerapp.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // POST /login: handle login requests and return a JWT if successful
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        // Let Spring Security authenticate username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // If authentication successful, generate JWT
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(request.getUsername());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}