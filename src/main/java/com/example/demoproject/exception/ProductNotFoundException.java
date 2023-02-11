package com.example.demoproject.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Integer productId) {
        super("A product with this id "+productId+" does not exist.");
    }

}
