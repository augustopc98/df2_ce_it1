package com.example.demo.services;

import com.example.demo.entities.CustomerOrder;
import com.example.demo.entities.OrderItem;
import com.example.demo.repositories.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepository;

    @Autowired
    public CustomerOrderServiceImpl(CustomerOrderRepository customerOrderRepository) {
        this.customerOrderRepository = customerOrderRepository;
    }

    @Override
    public CustomerOrder createOrder(String customerEmail, String customerAddress, Date orderDate, List<OrderItem> items) {
        CustomerOrder customerOrder = new CustomerOrder(customerEmail, customerAddress, orderDate, items);
        return customerOrderRepository.save(customerOrder);
    }

    @Override
    public CustomerOrder addOrderItem(Long orderId, OrderItem item) {
        Optional<CustomerOrder> customerOrderOpt = customerOrderRepository.findById(orderId);
        if (customerOrderOpt.isPresent()) {
            CustomerOrder customerOrder = customerOrderOpt.get();
            customerOrder.addOrderItem(item);
            return customerOrderRepository.save(customerOrder);
        }
        return null;
    }

    @Override
    public CustomerOrder removeOrderItem(Long orderId, Long orderItemId) {
        Optional<CustomerOrder> customerOrderOpt = customerOrderRepository.findById(orderId);
        if (customerOrderOpt.isPresent()) {
            CustomerOrder customerOrder = customerOrderOpt.get();
            customerOrder.getItems().removeIf(item -> item.getId().equals(orderItemId));
            return customerOrderRepository.save(customerOrder);
        }
        return null;
    }

    @Override
    public BigDecimal calculateTotal(Long orderId) {
        Optional<CustomerOrder> customerOrderOpt = customerOrderRepository.findById(orderId);
        return customerOrderOpt.map(CustomerOrder::calculateTotal).orElse(BigDecimal.ZERO);
    }

    @Override
    public void sendForDelivery(Long orderId) {
        Optional<CustomerOrder> customerOrderOpt = customerOrderRepository.findById(orderId);
        customerOrderOpt.ifPresent(customerOrder -> {
            customerOrder.sendForDelivery();
            customerOrderRepository.save(customerOrder);
        });
    }

    @Override
    public void updateDeliveryStatus(Long orderId, String status) {
        Optional<CustomerOrder> customerOrderOpt = customerOrderRepository.findById(orderId);
        customerOrderOpt.ifPresent(customerOrder -> {
            customerOrder.updateDeliveryStatus(status);
            customerOrderRepository.save(customerOrder);
        });
    }

    @Override
    public CustomerOrder findOrderById(Long orderId) {
        return customerOrderRepository.findById(orderId).orElse(null);
    }
}
