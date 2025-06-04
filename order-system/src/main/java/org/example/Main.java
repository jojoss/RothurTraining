package org.example;

import org.example.dao.pg.jdbc.UserPgJdbcDao;
import org.example.dao.pg.jdbc.OrderPgJdbcDao;
import org.example.dao.pg.hibernate.UserPgHibernateDao;
import org.example.dao.pg.hibernate.OrderPgHibernateDao;
import org.example.model.User;
import org.example.model.Order;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        UserPgJdbcDao userDao = new UserPgJdbcDao();
        OrderPgJdbcDao orderDao = new OrderPgJdbcDao();

        System.out.println("==== User DAO Tests ====");

        // Insert users
        userDao.insertUser(new User("Alice", "alice@example.com"));
        userDao.insertUser(new User("Bob", "bob@gmail.com"));

        // Get all users
        List<User> users = userDao.getAllUsers();
        System.out.println("All users:");
        users.forEach(System.out::println);

        // Get user by ID
        if (!users.isEmpty()) {
            User firstUser = userDao.getUserById(users.get(0).getId());
            System.out.println("Get user by ID: " + firstUser);
        }

        // Update user
        if (!users.isEmpty()) {
            User userToUpdate = users.get(0);
            userToUpdate.setUsername("Alice Updated");
            userToUpdate.setEmail("alice.new@example.com");
            userDao.updateUser(userToUpdate);
        }

        // Count users by email domain
        Map<String, Integer> domainCounts = userDao.countUsersByDomain();
        System.out.println("User count by domain: " + domainCounts);

        // Get user count
        int count = userDao.getUserCount();
        System.out.println("Total users: " + count);

        // ===== Order DAO Tests =====

        System.out.println("==== Order DAO Tests ====");

        if (!users.isEmpty()) {
            int userId = users.get(0).getId();

            // Insert orders
            orderDao.insertOrder(new Order("Phone", 2, 299.99, userId));
            orderDao.insertOrder(new Order("Laptop", 1, 999.99, userId));

            // Get all orders
            List<Order> orders = orderDao.getAllOrders();
            System.out.println("All orders:");
            orders.forEach(System.out::println);

            // Get order by ID
            if (!orders.isEmpty()) {
                Order order = orderDao.getOrderById(orders.get(0).getId());
                System.out.println("Order by ID: " + order);
            }

            // Update order
            if (!orders.isEmpty()) {
                Order toUpdate = orders.get(0);
                toUpdate.setQuantity(5);
                orderDao.updateOrder(toUpdate);
            }

            // Get orders by user ID
            List<Order> userOrders = orderDao.getOrdersByUserId(userId);
            System.out.println("Orders by User ID:");
            userOrders.forEach(System.out::println);

            // Get total sales
            double totalSales = orderDao.getTotalSales();
            System.out.println("Total sales: $" + totalSales);

            // Count orders by product
            Map<String, Integer> orderCounts = orderDao.countOrdersByProduct();
            System.out.println("Order count by product: " + orderCounts);

            // Get top products by revenue
            List<String> topProducts = orderDao.getTopProductsByRevenue();
            System.out.println("Top products by revenue:");
            topProducts.forEach(System.out::println);

            // Delete all orders of the user before deleting the user
            List<Order> userOrdersBeforeDelete = orderDao.getOrdersByUserId(userId);
            for (Order o : userOrdersBeforeDelete) {
                orderDao.deleteOrder(o.getId());
                System.out.println("Deleted order ID: " + o.getId());
            }

            // Now it's safe to delete the user
            userDao.deleteUser(userId);
            System.out.println("Deleted user ID: " + userId);
        }
    }
}
