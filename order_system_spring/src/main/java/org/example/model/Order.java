package org.example.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "product", nullable = false, length = 100)
    private String product;
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private double price;
    @Column(name = "user_id", nullable = false)
    private int userId;

    public Order() {}

    public Order(String product, int quantity, double price, int userId) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.userId = userId;
    }

    public Order(int id, String product, int quantity, double price, int userId) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.userId = userId;
    }

    // Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() && getQuantity() == order.getQuantity() && Double.compare(order.getPrice(), getPrice()) == 0 && getUserId() == order.getUserId() && getProduct().equals(order.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProduct(), getQuantity(), getPrice(), getUserId());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", userId=" + userId +
                '}';
    }
}