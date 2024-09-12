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
        this.productPrice = (productPrice != null) ? productPrice : BigDecimal.ZERO; // Ensure productPrice is never null
    }

    // Add getter method for id
    public Long getId() {
        return id;
    }

    // Getter method for productPrice
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    // Setter method for productPrice
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    // Method to calculate the price, handling the case where productPrice is null
    public BigDecimal getPrice() {
        if (productPrice == null) {
            return BigDecimal.ZERO; // Avoid null pointer exception
        }
        return productPrice.multiply(new BigDecimal(quantity));
    }

    // Getters and Setters for other fields
}
