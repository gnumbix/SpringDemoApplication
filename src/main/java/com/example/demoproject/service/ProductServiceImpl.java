package com.example.demoproject.service;

import com.example.demoproject.domain.Product;
import com.example.demoproject.model.ProductForm;
import com.example.demoproject.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    @Transactional
    public Product add(ProductForm form) {
        Product product = new Product();
        product.setName(form.getName());
        product.setStatus(form.getStatus());
        return this.productRepository.save(product);
    }

    @Override
    @Transactional
    public Product update(@Valid ProductForm form) {
        Optional<Product> productOpt = this.findById(form.getId());
        if(productOpt.isEmpty()) {
            return null;
        }
        Product product = productOpt.get();
        product.setName(form.getName());
        product.setStatus(form.getStatus());
        return product;
    }

}
