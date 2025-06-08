package org.example.controller;

import org.example.dao.pg.jdbc.OrderPgJdbcDao;
import org.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderPgJdbcDao orderDao;

    @Autowired
    public OrderController(OrderPgJdbcDao orderDao) {
        this.orderDao = orderDao;
    }

    // GET /orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    // GET /orders/{id}
    @GetMapping("/{id}")
    public Object getOrderById(@PathVariable int id) {
        Order order = orderDao.getOrderById(id);
        return (order != null) ? order : Map.of("error", "Order not found");
    }

    // GET /orders/user/{userId}
    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable int userId) {
        return orderDao.getOrdersByUserId(userId);
    }

    // ✅ 用已有的 countOrdersByProduct() 替代原来的 /stats
    @GetMapping("/stats")
    public Map<String, Integer> getOrderStatsByProduct() {
        return orderDao.countOrdersByProduct();
    }

    // ✅ 用已有的 getTopProductsByRevenue() 替代原来的 /top
    @GetMapping("/top-products")
    public List<String> getTopProductsByRevenue() {
        return orderDao.getTopProductsByRevenue();
    }

    // ✅ 新增一个 /total-sales（你有 getTotalSales 方法）
    @GetMapping("/total-sales")
    public Map<String, Double> getTotalSales() {
        return Map.of("totalSales", orderDao.getTotalSales());
    }

    // POST /orders
    @PostMapping
    public Map<String, String> addOrder(@RequestBody Order newOrder) {
        orderDao.insertOrder(newOrder);
        return Map.of("message", "Order created successfully");
    }

    // PUT /orders
    @PutMapping
    public Map<String, String> updateOrder(@RequestBody Order updatedOrder) {
        orderDao.updateOrder(updatedOrder);
        return Map.of("message", "Order updated successfully");
    }

    // DELETE /orders/{id}
    @DeleteMapping("/{id}")
    public Map<String, String> deleteOrder(@PathVariable int id) {
        orderDao.deleteOrder(id);
        return Map.of("message", "Order deleted successfully");
    }
}

