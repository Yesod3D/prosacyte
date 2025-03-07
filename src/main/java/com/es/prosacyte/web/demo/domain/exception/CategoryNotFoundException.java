package com.es.prosacyte.web.demo.domain.exception;

public class CategoryNotFoundException extends RuntimeException {

    // Constructor para buscar por ID
    public CategoryNotFoundException(Long categoryId) {
        super("Categoría no encontrada con ID: " + categoryId);
    }

    // Constructor para mensajes personalizados
    public CategoryNotFoundException(String message) {
        super(message);
    }
}