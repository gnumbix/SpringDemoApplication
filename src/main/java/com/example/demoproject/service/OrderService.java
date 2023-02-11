package com.example.demoproject.service;

import com.example.demoproject.domain.Order;
import com.example.demoproject.model.OrderForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAll();

    Optional<Order> findById(Integer orderId);

    Order add(OrderForm form);

    Order update(OrderForm form);
}
