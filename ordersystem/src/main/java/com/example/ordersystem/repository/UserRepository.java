package com.example.ordersystem.repository;

import com.example.ordersystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 不需要写实现，Spring Boot 会自动帮你实现
}
