package com.example.ordersystem.service;

import com.example.ordersystem.model.User;
import com.example.ordersystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This service handles business logic related to User operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final LogService logService;

    /**
     * Constructor injection for UserRepository and LogService.
     * Spring will automatically inject the dependencies.
     */
    @Autowired
    public UserService(UserRepository userRepository, LogService logService) {
        this.userRepository = userRepository;
        this.logService = logService;
    }

    /**
     * Fetch all users from the database.
     */
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        logService.log("Fetched all users. Total: " + users.size());
        return users;
    }

    /**
     * Save a new user to the database.
     */
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        logService.log("Created user with ID: " + savedUser.getId());
        return savedUser;
    }

    /**
     * Delete a user by their ID.
     */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        logService.log("Deleted user with ID: " + id);
    }

    /**
     * Find a user by their ID.
     */
    public Optional<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        logService.log("Fetched user with ID: " + id + ". Found: " + user.isPresent());
        return user;
    }
}