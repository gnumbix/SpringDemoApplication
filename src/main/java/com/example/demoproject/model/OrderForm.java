package com.example.demoproject.model;

import com.example.demoproject.domain.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.StringJoiner;

public class OrderForm {
    private Integer id;

    @NotNull(message = "Name cannot be null.")
    @Size(min=2, max=200, message = "Name to short")
    private String name;

    @NotNull(message = "Product id cannot be null.")
    private Integer productId;

    @NotNull(message = "Status cannot be null.")
    private OrderStatus status;
    private Integer qty;

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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("status=" + status)
                .add("productId=" + productId)
                .add("qty=" + qty)
                .toString();
    }
}
