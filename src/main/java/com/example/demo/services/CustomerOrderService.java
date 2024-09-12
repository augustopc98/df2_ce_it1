package com.example.demo.services;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.entities.OrderItem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CustomerOrderService {

    CustomerOrder createOrder(String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items);

    CustomerOrder addOrderItem(Long orderId, OrderItem item);

    CustomerOrder removeOrderItem(Long orderId, Long orderItemId);

    BigDecimal calculateTotal(Long orderId);

    void sendForDelivery(Long orderId);

    void updateDeliveryStatus(Long orderId, String status);

    CustomerOrder findOrderById(Long orderId);
}
