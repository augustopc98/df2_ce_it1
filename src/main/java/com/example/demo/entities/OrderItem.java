package com.example.demo.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private int quantity;
    private BigDecimal productPrice;

    // Constructors, Getters, Setters, and domain methods
    public OrderItem() {}

    public OrderItem(Long productId, int quantity, BigDecimal productPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.productPrice = productPrice;
    }

    public BigDecimal getPrice() {
        return productPrice.multiply(new BigDecimal(quantity));
    }

    // Getters and Setters
}
