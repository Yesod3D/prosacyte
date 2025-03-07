package com.es.prosacyte.web.demo.domain.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long categoryId) {
        super("Category for product with ID " + categoryId + " not found.");
    }
}
