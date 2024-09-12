package com.example.demo.controllers;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.entities.OrderItem;
import com.example.demo.services.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/customer-orders")
public class CustomerOrderController {

    private final CustomerOrderService customerOrderService;

    @Autowired
    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @PostMapping
    public CustomerOrder createOrder(@RequestBody CustomerOrder customerOrder) {
        return customerOrderService.createOrder(
                customerOrder.getCustomerEmail(),
                customerOrder.getCustomerAddress(),
                customerOrder.getOrderDate(),
                customerOrder.getItems()
        );
    }

    @PostMapping("/{orderId}/items")
    public CustomerOrder addOrderItem(
            @PathVariable Long orderId,
            @RequestBody OrderItem item
    ) {
        return customerOrderService.addOrderItem(orderId, item);
    }

    @DeleteMapping("/{orderId}/items/{orderItemId}")
    public CustomerOrder removeOrderItem(
            @PathVariable Long orderId,
            @PathVariable Long orderItemId
    ) {
        return customerOrderService.removeOrderItem(orderId, orderItemId);
    }

    @GetMapping("/{orderId}/total")
    public BigDecimal calculateTotal(
            @PathVariable Long orderId
    ) {
        return customerOrderService.calculateTotal(orderId);
    }

    @PostMapping("/{orderId}/send")
    public void sendForDelivery(@PathVariable Long orderId) {
        customerOrderService.sendForDelivery(orderId);
    }

    @PostMapping("/{orderId}/status")
    public void updateDeliveryStatus(
            @PathVariable Long orderId,
            @RequestBody String status
    ) {
        customerOrderService.updateDeliveryStatus(orderId, status);
    }

    @GetMapping("/{orderId}")
    public CustomerOrder findOrderById(@PathVariable Long orderId) {
        return customerOrderService.findOrderById(orderId);
    }
}
