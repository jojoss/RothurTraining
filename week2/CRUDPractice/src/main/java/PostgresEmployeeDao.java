import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    public void insertEmployee(Employee emp) {
        String sql = "INSERT INTO employees (first_name, last_name, email, password, flagged) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, emp.getFirstName());
            pstmt.setString(2, emp.getLastName());
            pstmt.setString(3, emp.getEmail());
            pstmt.setInt(4, emp.getPassword());
            pstmt.setBoolean(5, emp.getFlagged());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee inserted successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Failed to insert employee:");
            e.printStackTrace();
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getInt("password"),
                        rs.getBoolean("flagged"),
                        null
                );
                employees.add(emp);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve employees:");
            e.printStackTrace();
        }

        return employees;
    }

    public Employee getEmployeeById(int id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        Employee emp = null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                emp = new Employee(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getInt("password"),
                        rs.getBoolean("flagged"),
                        null
                );
            }

        } catch (SQLException e) {
            System.out.println(" Failed to find employee by ID:");
            e.printStackTrace();
        }

        return emp;
    }

    public void updateEmployeeEmail(int id, String newEmail) {
        String sql = "UPDATE employees SET email = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newEmail);
            pstmt.setInt(2, id);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee email updated successfully!");
            } else {
                System.out.println("No employee found with ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to update employee:");
            e.printStackTrace();
        }
    }

    public void deleteEmployeeById(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee with ID " + id + " deleted successfully.");
            } else {
                System.out.println("No employee found with ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Failed to delete employee:");
            e.printStackTrace();
        }
    }

}
