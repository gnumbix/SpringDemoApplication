package com.example.demoproject.service;

import com.example.demoproject.domain.Item;
import com.example.demoproject.domain.Order;
import com.example.demoproject.domain.enums.ItemStatus;
import com.example.demoproject.model.OrderForm;
import com.example.demoproject.repository.ItemRepository;
import com.example.demoproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ItemRepository itemRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
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
    @Transactional
    public Order add(OrderForm form) {

        List<Item> itemsList = itemRepository.findAllByStatus(ItemStatus.ACTIVE, PageRequest.of(0, form.getQty()));

        Order order = new Order();
        order.setName(form.getName());
        order.setStatus(form.getStatus());
        order.setItemsQuantity(itemsList.size());
        order = this.orderRepository.save(order);

        for(Item item : itemsList) {
            item.setStatus(ItemStatus.RESERVED);
            item.setOrderId(order.getId());
            item.setOrder(order);
        }

        return order;
    }

    @Override
    @Transactional
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
