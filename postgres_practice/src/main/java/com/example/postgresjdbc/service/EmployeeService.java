package com.example.postgresjdbc.service;

import com.example.postgresjdbc.dao.DepartmentDao;
import com.example.postgresjdbc.dao.EmployeeDao;
import com.example.postgresjdbc.model.Department;
import com.example.postgresjdbc.model.Employee;
import com.example.postgresjdbc.DatabaseConfig;

import java.sql.Connection;

public class EmployeeService {
    private final EmployeeDao employeeDao = new EmployeeDao();
    private final DepartmentDao departmentDao = new DepartmentDao();

    public void hireEmployeeWithDepartment(Employee emp, Department dept) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            conn.setAutoCommit(false);  // Start transaction

            System.out.println("Starting transaction to insert employee and department...");

            employeeDao.insert(conn, emp);
            System.out.println("Employee inserted successfully.");

            departmentDao.insert(conn, dept);
            System.out.println("Department inserted successfully.");

            conn.commit();
            System.out.println("Transaction committed successfully. Employee hiring complete.");

        } catch (Exception e) {
            System.err.println("Exception occurred: " + e.getMessage());
            try {
                DatabaseConfig.getConnection().rollback();
                System.err.println("Transaction rolled back due to failure.");
            } catch (Exception rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
            }
        }
    }
}
