package com.example.postgresjdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {

    // Create
    public void insertDepartment(Department dept) {
        String sql = "INSERT INTO department (name, employee_email) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dept.getName());
            stmt.setString(2, dept.getEmployeeEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Insert department failed: " + e.getMessage());
        }
    }

    // Read
    public Department getDepartmentById(int id) {
        String sql = "SELECT * FROM department WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Department(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("employee_email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Get department failed: " + e.getMessage());
        }
        return null;
    }

    public List<Department> getAllDepartments() {
        List<Department> list = new ArrayList<>();
        String sql = "SELECT * FROM department";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Department(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("employee_email")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Get all departments failed: " + e.getMessage());
        }
        return list;
    }

    // Update
    public void updateDepartmentName(int id, String newName) {
        String sql = "UPDATE department SET name = ? WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Update department failed: " + e.getMessage());
        }
    }

    // Delete
    public void deleteDepartment(int id) {
        String sql = "DELETE FROM department WHERE id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Delete department failed: " + e.getMessage());
        }
    }

    // Join
    public void printDepartmentsWithEmployees() {
        String sql = "SELECT d.id, d.name, e.first_name, e.last_name " +
                "FROM department d JOIN employee e ON d.employee_email = e.email";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.printf("Dept ID: %d, Name: %s, Employee: %s %s\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("first_name"),
                        rs.getString("last_name"));
            }

        } catch (SQLException e) {
            System.err.println("Join query failed: " + e.getMessage());
        }
    }
}
