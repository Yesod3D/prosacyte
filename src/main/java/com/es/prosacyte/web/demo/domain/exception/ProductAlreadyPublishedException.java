package com.es.prosacyte.web.demo.domain.exception;

public class ProductAlreadyPublishedException extends RuntimeException {

    // Constructor básico con ID del producto
    public ProductAlreadyPublishedException(Long productId) {
        super("El producto con ID " + productId + " ya está publicado y no puede ser modificado");
    }

    // Constructor para mensajes personalizados avanzados
    public ProductAlreadyPublishedException(String message) {
        super(message);
    }
}