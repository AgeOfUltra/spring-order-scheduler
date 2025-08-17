package com.scheduler.springorderscheduler.service;

import com.scheduler.springorderscheduler.Dto.OrderRequest;
import com.scheduler.springorderscheduler.entity.Order;
import com.scheduler.springorderscheduler.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderRequest request) {
        Order order = new Order();
        order.setCustomerEmail(request.getCustomerEmail());
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
