//package org.example.servlet;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.example.dao.pg.jdbc.OrderPgJdbcDao;
//import org.example.model.Order;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//@WebServlet("/orders/*")
//public class OrderServlet extends HttpServlet {
//
//    private final OrderPgJdbcDao orderDao = new OrderPgJdbcDao();
//    private final ObjectMapper objectMapper = new ObjectMapper(); // For JSON conversion
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String pathInfo = req.getPathInfo(); // e.g., /1, /user/3, /stats, /top
//        resp.setContentType("application/json");
//
//        if (pathInfo == null || pathInfo.equals("/")) {
//            // Return all orders
//            List<Order> orders = orderDao.getAllOrders();
//            objectMapper.writeValue(resp.getWriter(), orders);
//            return;
//        }
//
//        String[] parts = pathInfo.substring(1).split("/");
//
//        try {
//            if (parts.length == 1) {
//                // GET /orders/{id}
//                int id = Integer.parseInt(parts[0]);
//                Order order = orderDao.getOrderById(id);
//                if (order != null) {
//                    objectMapper.writeValue(resp.getWriter(), order);
//                } else {
//                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                    resp.getWriter().write("{\"error\":\"Order not found\"}");
//                }
//            } else if (parts.length == 2 && parts[0].equals("user")) {
//                // GET /orders/user/{userId}
//                int userId = Integer.parseInt(parts[1]);
//                List<Order> orders = orderDao.getOrdersByUserId(userId);
//                objectMapper.writeValue(resp.getWriter(), orders);
//            } else if (parts.length == 1 && parts[0].equals("stats")) {
//                // GET /orders/stats
//                Map<String, Integer> stats = orderDao.countOrdersByProduct();
//                objectMapper.writeValue(resp.getWriter(), stats);
//            } else if (parts.length == 1 && parts[0].equals("top")) {
//                // GET /orders/top
//                List<String> topProducts = orderDao.getTopProductsByRevenue();
//                objectMapper.writeValue(resp.getWriter(), topProducts);
//            } else if (parts.length == 1 && parts[0].equals("total-sales")) {
//                // GET /orders/total-sales
//                double totalSales = orderDao.getTotalSales();
//                objectMapper.writeValue(resp.getWriter(), Map.of("totalSales", totalSales));
//            } else {
//                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//                resp.getWriter().write("{\"error\":\"Invalid request path\"}");
//            }
//        } catch (NumberFormatException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            resp.getWriter().write("{\"error\":\"Invalid numeric value in path\"}");
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Order newOrder = objectMapper.readValue(req.getReader(), Order.class);
//        orderDao.insertOrder(newOrder);
//        resp.setStatus(HttpServletResponse.SC_CREATED);
//        resp.getWriter().write("{\"message\":\"Order created successfully\"}");
//    }
//
//    @Override
//    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        Order updatedOrder = objectMapper.readValue(req.getReader(), Order.class);
//        orderDao.updateOrder(updatedOrder);
//        resp.getWriter().write("{\"message\":\"Order updated successfully\"}");
//    }
//
//    @Override
//    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String pathInfo = req.getPathInfo(); // e.g., /3
//        try {
//            int id = Integer.parseInt(pathInfo.substring(1));
//            orderDao.deleteOrder(id);
//            resp.getWriter().write("{\"message\":\"Order deleted successfully\"}");
//        } catch (NumberFormatException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            resp.getWriter().write("{\"error\":\"Invalid order ID\"}");
//        }
//    }
//}