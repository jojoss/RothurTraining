package org.example.dao.pg.hibernate;

import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

/**
 * This class handles all database operations for User table using Hibernate.
 * Includes basic CRUD and advanced HQL operations.
 */
public class UserPgHibernateDao {

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    /**
     * Save a new user to the database.
     */
    public void saveUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }
    }

    /**
     * Retrieve a user by their ID.
     */
    public User getUserById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    /**
     * Retrieve all users from the database.
     */
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    /**
     * Update an existing user.
     */
    public void updateUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        }
    }

    /**
     * Delete a user by entity.
     */
    public void deleteUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(user);
            session.getTransaction().commit();
        }
    }

    /**
     * Count users grouped by their email domain.
     * Example: gmail.com → 10 users
     */
    public Map<String, Long> countUsersByDomain() {
        Map<String, Long> result = new HashMap<>();
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> rows = session.createQuery(
                    "SELECT substring(u.email, locate('@', u.email) + 1), count(u) " +
                            "FROM User u GROUP BY substring(u.email, locate('@', u.email) + 1)",
                    Object[].class
            ).list();
            for (Object[] row : rows) {
                String domain = (String) row[0];
                Long count = (Long) row[1];
                result.put(domain, count);
            }
        }
        return result;
    }

    /**
     * Get users who have at least one order.
     * Relies on a join with the Order table.
     */
    public List<User> findUsersWithOrders() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT DISTINCT u FROM User u WHERE u.id IN (SELECT o.userId FROM Order o)",
                    User.class
            ).list();
        }
    }

    /**
     * Get total number of users.
     */
    public long getUserCount() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT COUNT(u) FROM User u", Long.class).uniqueResult();
        }
    }
}