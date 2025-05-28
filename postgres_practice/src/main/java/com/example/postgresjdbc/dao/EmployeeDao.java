package com.example.postgresjdbc.dao;

import com.example.postgresjdbc.DatabaseConfig;
import com.example.postgresjdbc.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    // Create
    public void insertEmployee(Employee employee) {
        String sql = "INSERT INTO employee (first_name, last_name, email, password, flagged) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getEmail());
            stmt.setInt(4, employee.getPassword());
            stmt.setBoolean(5, employee.getFlagged());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Insert failed: " + e.getMessage());
        }
    }

    // Read
    public Employee getEmployeeByEmail(String email) {
        String sql = "SELECT * FROM employee WHERE email = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getInt("password"),
                        rs.getBoolean("flagged"),
                        new ArrayList<>()
                );
            }
        } catch (SQLException e) {
            System.err.println("Read failed: " + e.getMessage());
        }
        return null;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Employee(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getInt("password"),
                        rs.getBoolean("flagged"),
                        new ArrayList<>()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Read all failed: " + e.getMessage());
        }
        return list;
    }

    // Update
    public void updateEmployeeFlag(String email, boolean flagged) {
        String sql = "UPDATE employee SET flagged = ? WHERE email = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, flagged);
            stmt.setString(2, email);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Update failed: " + e.getMessage());
        }
    }

    // Delete
    public void deleteEmployee(String email) {
        String sql = "DELETE FROM employee WHERE email = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Delete failed: " + e.getMessage());
        }
    }

    public void insert(Connection conn, Employee emp) throws SQLException {
        String sql = "INSERT INTO employee (first_name, last_name, email, password, flagged) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setString(3, emp.getEmail());
            stmt.setInt(4, emp.getPassword());
            stmt.setBoolean(5, emp.getFlagged());
            stmt.executeUpdate();
        }
    }
}
