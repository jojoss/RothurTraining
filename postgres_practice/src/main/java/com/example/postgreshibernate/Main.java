package com.example.postgreshibernate;

import com.example.postgreshibernate.model.Employee;
import com.example.postgreshibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            Employee employee = new Employee();
            employee.setEmail("john.doe@example.com");
            employee.setFirstName("John");
            employee.setLastName("Doe");

            session.save(employee);

            transaction.commit();

            System.out.println("Employee saved successfully!");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
