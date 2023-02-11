package com.example.demoproject.domain;


import com.example.demoproject.domain.enums.ProductStatus;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name="itemsQuantity", nullable = false)
    private Integer itemsQuantity;

    @Column(name="itemsSuspendedQuantity", nullable = false)
    private Integer itemsSuspendedQuantity;

    @OneToMany(mappedBy="product",fetch = FetchType.LAZY)
    private List<Item> items;

    public Product() {
        this.itemsQuantity = 0;
        this.itemsSuspendedQuantity = 0;
    }

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

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public Integer getItemsQuantity() {
        return itemsQuantity;
    }

    public void setItemsQuantity(Integer itemsQuantity) {
        this.itemsQuantity = itemsQuantity;
    }

    public Integer getItemsSuspendedQuantity() {
        return itemsSuspendedQuantity;
    }

    public void setItemsSuspendedQuantity(Integer itemsSuspendedQuantity) {
        this.itemsSuspendedQuantity = itemsSuspendedQuantity;
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
        Product product = (Product) o;
        return Objects.equals(id, product.id) && name.equals(product.name) && status == product.status && itemsQuantity.equals(product.itemsQuantity) && itemsSuspendedQuantity.equals(product.itemsSuspendedQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, itemsQuantity, itemsSuspendedQuantity);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("status=" + status)
                .add("itemsQuantity=" + itemsQuantity)
                .add("itemsSuspendedQuantity=" + itemsSuspendedQuantity)
                .toString();
    }
}
