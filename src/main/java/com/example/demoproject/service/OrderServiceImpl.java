package com.example.demoproject.service;

import com.example.demoproject.domain.Item;
import com.example.demoproject.domain.Order;
import com.example.demoproject.domain.enums.ItemStatus;
import com.example.demoproject.exception.ProductNotFoundException;
import com.example.demoproject.model.OrderForm;
import com.example.demoproject.repository.ItemRepository;
import com.example.demoproject.repository.OrderRepository;
import com.example.demoproject.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ItemRepository itemRepository;

    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ItemRepository itemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.productRepository = productRepository;
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

        if(productRepository.findById(form.getProductId()).isEmpty()) {
            throw new ProductNotFoundException(form.getProductId());
        }

        List<Item> itemsList = itemRepository.findAllByStatusAndProductId(
                ItemStatus.ACTIVE, form.getProductId(), PageRequest.of(0, form.getQty()));

        Order order = new Order();
        order.setName(form.getName());
        order.setStatus(form.getStatus());
        order.setProductId(form.getProductId());
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
        order.setProductId(form.getProductId());
        return order;
    }
}
