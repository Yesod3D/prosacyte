package com.es.prosacyte.web.demo.domain.exception;

public class ProductAlreadyPublishedException extends RuntimeException {
    public ProductAlreadyPublishedException(Long productId) {
        super("El producto con ID " + productId + " ya está publicado");
    }
}
