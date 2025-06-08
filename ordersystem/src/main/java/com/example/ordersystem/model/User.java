package com.example.ordersystem.model;

import jakarta.persistence.*;

@Entity                    // 表示这是一个 JPA 实体类
@Table(name = "users")     // 映射到数据库中的 users 表
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;       // 对应数据库中的 SERIAL 主键

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 100)
    private String email;

    // 构造方法
    public User() {}

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Getter & Setter（推荐你用 IDEA 快捷键生成）
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
