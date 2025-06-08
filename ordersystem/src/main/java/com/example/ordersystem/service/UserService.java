package com.example.ordersystem.service;

import com.example.ordersystem.model.User;
import com.example.ordersystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 告诉 Spring 这是一个业务逻辑层的组件
public class UserService {

    private final UserRepository userRepository;

    // 使用构造器注入（推荐），Spring 会自动注入依赖的 Repository 实现类
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 获取所有用户
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 添加用户
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // 你可以继续扩展：deleteUser, findById 等
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
