package com.example.demoproject.model;

import com.example.demoproject.domain.enums.ProductStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.StringJoiner;

public class ProductForm {

    private Integer id;
    @NotNull(message = "Status cannot be null.")
    private ProductStatus status;

    @NotNull(message = "Name cannot be null.")
    @Size(min=2, max=200, message = "Name to short")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ProductForm.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("status=" + status)
                .add("name='" + name + "'")
                .toString();
    }
}
