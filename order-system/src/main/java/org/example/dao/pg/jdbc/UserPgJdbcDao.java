package org.example.dao.pg.jdbc;

import org.example.model.User;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * This class handles all database operations for User table using PostgreSQL and JDBC.
 * Includes basic CRUD and advanced SQL operations.
 */
public class UserPgJdbcDao {

    private Connection connection;

    /**
     * Constructor that initializes the PostgreSQL connection using db.properties file.
     */
    public UserPgJdbcDao() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            String url = prop.getProperty("pg.url");
            String user = prop.getProperty("pg.user");
            String password = prop.getProperty("pg.password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a new user into the database.
     */
    public void insertUser(User user) {
        String sql = "INSERT INTO users (username, email) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve a user by their ID.
     */
    public User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieve all users from the database.
     */
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        return getUsers(users, sql);
    }

    private List<User> getUsers(List<User> users, String sql) {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("email")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * Update the information of a user.
     */
    public void updateUser(User user) {
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a user by their ID.
     */
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Count users grouped by their email domain (e.g., gmail.com, yahoo.com).
     */
    public Map<String, Integer> countUsersByDomain() {
        String sql = "SELECT SUBSTRING(email FROM POSITION('@' IN email) + 1) AS domain, COUNT(*) as total FROM users GROUP BY domain";
        Map<String, Integer> result = new HashMap<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                result.put(rs.getString("domain"), rs.getInt("total"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Get a list of users who have placed orders (based on join with the 'orders' table).
     */
    public List<User> findUsersWithOrders() {
        String sql = "SELECT u.id, u.username, u.email FROM users u JOIN orders o ON u.id = o.user_id GROUP BY u.id, u.username, u.email";
        List<User> users = new ArrayList<>();
        return getUsers(users, sql);
    }

    /**
     * Get the total number of users in the database.
     */
    public int getUserCount() {
        String sql = "SELECT COUNT(*) as total FROM users";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
