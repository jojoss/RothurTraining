package com.example.postgresjdbc;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            if (conn != null) {
                System.out.println("PostgreSQL database connection succeeded.");
            } else {
                System.out.println("PostgreSQL database connection failed.");
            }
        } catch (Exception e) {
            System.err.println("PostgreSQL database connection error: : " + e.getMessage());
        }
    }
}
