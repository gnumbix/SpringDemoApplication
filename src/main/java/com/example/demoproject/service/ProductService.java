package com.example.demoproject.service;

import com.example.demoproject.domain.Product;
import com.example.demoproject.model.ProductForm;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Integer productId);

    Product add(ProductForm form);

    Product update(@Valid ProductForm form);
}
