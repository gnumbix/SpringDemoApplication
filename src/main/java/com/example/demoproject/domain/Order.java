package com.example.demoproject.domain;

import com.example.demoproject.domain.enums.OrderStatus;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name="itemsQuantity", nullable = false)
    private Integer itemsQuantity;

    @OneToMany(mappedBy="orderId",fetch = FetchType.LAZY)
    private List<Item> items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(Integer itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && name.equals(order.name) && status == order.status && itemsQuantity.equals(order.itemsQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, itemsQuantity);
    }
}
