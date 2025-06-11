package org.example.controller;

import org.example.dao.pg.jdbc.UserPgJdbcDao;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserPgJdbcDao userDao;

    @Autowired
    public UserController(UserPgJdbcDao userDao) {
        this.userDao = userDao;
    }

    // GET /users
    @GetMapping
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    // GET /users/{id}
    @GetMapping("/{id}")
    public Object getUserById(@PathVariable int id) {
        User user = userDao.getUserById(id);
        return (user != null) ? user : Map.of("error", "User not found");
    }

    // GET /users/count
    @GetMapping("/count")
    public Map<String, Integer> getUserCount() {
        int count = userDao.getUserCount();
        return Map.of("totalUsers", count);
    }

    // GET /users/stats
    @GetMapping("/stats")
    public Map<String, Integer> getUserStats() {
        return userDao.countUsersByDomain();
    }

    // GET /users/with-orders
    @GetMapping("/with-orders")
    public List<User> getUsersWithOrders() {
        return userDao.findUsersWithOrders();
    }

    // POST /users
    @PostMapping
    public Map<String, String> addUser(@RequestBody User newUser) {
        userDao.insertUser(newUser);
        return Map.of("message", "User created successfully");
    }

    // PUT /users
    @PutMapping
    public Map<String, String> updateUser(@RequestBody User updatedUser) {
        userDao.updateUser(updatedUser);
        return Map.of("message", "User updated successfully");
    }

    // DELETE /users/{id}
    @DeleteMapping("/{id}")
    public Map<String, String> deleteUser(@PathVariable int id) {
        userDao.deleteUser(id);
        return Map.of("message", "User deleted successfully");
    }
}