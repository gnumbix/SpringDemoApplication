package com.example.demoproject.domain;

import com.example.demoproject.domain.enums.ItemStatus;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemStatus status;

    @Column(name="productId")
    private Integer productId;

    @Column(name="orderId")
    private Integer orderId;

    @Column(name="content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "productId", insertable = false, updatable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "orderId", insertable = false, updatable = false)
    private Order order;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && name.equals(item.name) && status == item.status && productId.equals(item.productId) && Objects.equals(orderId, item.orderId) && content.equals(item.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, productId, orderId, content);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("status=" + status)
                .add("productId=" + productId)
                .add("orderId=" + orderId)
                .add("content='" + content + "'")
                .toString();
    }
}
