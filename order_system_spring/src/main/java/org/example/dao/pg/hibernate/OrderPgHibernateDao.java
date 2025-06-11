package org.example.dao.pg.hibernate;

import org.example.model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.*;

/**
 * DAO class for PostgreSQL using Hibernate to manage Order data.
 */
public class OrderPgHibernateDao {

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    // =============== Basic CRUD ===============

    /**
     * Insert a new order into the database.
     */
    public void insertOrder(Order order) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(order);
            session.getTransaction().commit();
        }
    }

    /**
     * Get order by ID.
     */
    public Order getOrderById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Order.class, id);
        }
    }

    /**
     * Get all orders.
     */
    public List<Order> getAllOrders() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Order", Order.class).list();
        }
    }

    /**
     * Update order.
     */
    public void updateOrder(Order order) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(order);
            session.getTransaction().commit();
        }
    }

    /**
     * Delete order by entity.
     */
    public void deleteOrder(Order order) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(order);
            session.getTransaction().commit();
        }
    }

    // =============== Advanced Queries ===============

    /**
     * Get total sales revenue: SUM(quantity * price)
     */
    public double getTotalSales() {
        try (Session session = sessionFactory.openSession()) {
            Double result = session.createQuery(
                            "SELECT SUM(o.quantity * o.price) FROM Order o", Double.class)
                    .uniqueResult();
            return result != null ? result : 0.0;
        }
    }

    /**
     * Count how many orders per product: GROUP BY product
     */
    public Map<String, Long> countOrdersByProduct() {
        Map<String, Long> result = new HashMap<>();
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> rows = session.createQuery(
                            "SELECT o.product, COUNT(o) FROM Order o GROUP BY o.product", Object[].class)
                    .list();
            for (Object[] row : rows) {
                result.put((String) row[0], (Long) row[1]);
            }
        }
        return result;
    }

    /**
     * Get top 3 products by revenue: SUM(quantity * price) GROUP BY product ORDER BY DESC LIMIT 3
     */
    public List<String> getTopProductsByRevenue() {
        List<String> result = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            List<Object[]> rows = session.createQuery(
                            "SELECT o.product, SUM(o.quantity * o.price) AS revenue " +
                                    "FROM Order o GROUP BY o.product ORDER BY revenue DESC", Object[].class)
                    .setMaxResults(3)
                    .list();
            for (Object[] row : rows) {
                result.add(row[0] + " ($" + row[1] + ")");
            }
        }
        return result;
    }

    /**
     * Get all orders for a specific user by userId using Hibernate.
     */
    public List<Order> getOrdersByUserId(int userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM Order o WHERE o.userId = :userId", Order.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }
}