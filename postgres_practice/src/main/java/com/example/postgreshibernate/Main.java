package com.example.postgreshibernate;

import com.example.postgreshibernate.model.Employee;
import com.example.postgreshibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        // 获取 Hibernate 的 Session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            // 开启事务
            transaction = session.beginTransaction();

            // 创建并保存对象
            Employee employee = new Employee();
            employee.setEmail("john.doe@example.com");
            employee.setFirstName("John");
            employee.setLastName("Doe");

            // 保存到数据库
            session.save(employee);

            // 提交事务
            transaction.commit();

            System.out.println("Employee saved successfully!");

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error: " + e.getMessage());
        } finally {
            // 关闭 session
            session.close();
        }
    }
}
