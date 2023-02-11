package com.example.demoproject.service;

import com.example.demoproject.domain.Order;
import com.example.demoproject.model.OrderForm;
import com.example.demoproject.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(Integer orderId) {
        return this.orderRepository.findById(orderId);
    }

    @Override
    public Order add(OrderForm form) {
        Order order = new Order();
        order.setName(form.getName());
        order.setStatus(form.getStatus());
        return this.orderRepository.save(order);
    }

    @Override
    public Order update(OrderForm form) {
        Optional<Order> orderOpt = this.findById(form.getId());
        if(orderOpt.isEmpty()) {
            return null;
        }
        Order order = orderOpt.get();
        order.setName(form.getName());
        order.setStatus(form.getStatus());
        return order;
    }
}
