package com.example.callerapp.controller;

import com.example.callerapp.service.ForwardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway")
public class ForwardController {

    private final ForwardService forwardService;

    public ForwardController(ForwardService forwardService) {
        this.forwardService = forwardService;
    }

    @GetMapping("/users")
    public String getAllUsers() {
        return forwardService.fetchAllUsers();
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable int id) {
        return forwardService.fetchUserById(id);
    }

    @PostMapping("/users")
    public String createUser(@RequestBody String userJson) {
        return forwardService.createUser(userJson);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id) {
        forwardService.deleteUserById(id);
    }

    // Test endpoint to verify JWT protection
    @GetMapping("/test")
    public ResponseEntity<String> protectedTest() {
        return ResponseEntity.ok("This is a protected endpoint. You are authenticated!");
    }
}
