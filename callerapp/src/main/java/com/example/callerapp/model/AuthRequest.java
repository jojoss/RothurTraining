package com.example.callerapp.model;

import lombok.Data;

/**
 * DTO for login request payload: username and password.
 */
@Data
public class AuthRequest {
    private String username;
    private String password;
}