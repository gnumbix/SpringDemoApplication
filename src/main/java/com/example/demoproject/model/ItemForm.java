package com.example.demoproject.model;

import com.example.demoproject.domain.enums.ItemStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.StringJoiner;

public class ItemForm {
    private Integer id;

    @NotNull(message = "Name cannot be null.")
    @Size(min=2, max=200, message = "Name to short")
    private String name;

    @NotNull(message = "Status cannot be null.")
    private ItemStatus status;

    @NotNull(message = "Product id cannot be null.")
    private Integer productId;
    private Integer orderId;
    private String content;

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

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ItemForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("status=" + status)
                .add("productId=" + productId)
                .add("orderId=" + orderId)
                .add("content='" + content + "'")
                .toString();
    }
}
