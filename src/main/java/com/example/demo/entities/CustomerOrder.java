package com.example.demo.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerEmail;
    private String customerAddress;
    private Date orderDate;
    private String deliveryStatus;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    // Constructors, Getters, Setters, and domain methods
    public CustomerOrder() {}

    public CustomerOrder(String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items) {
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.orderDate = orderDate;
        this.items = items;
    }

    // Add missing getters
    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addOrderItem(OrderItem item) {
        items.add(item);
    }

    public void removeOrderItem(OrderItem item) {
        items.remove(item);
    }

    public BigDecimal calculateTotal() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void sendForDelivery() {
        this.deliveryStatus = "Sent for delivery";
    }

    public void updateDeliveryStatus(String status) {
        this.deliveryStatus = status;
    }
}
