package com.es.prosacyte.web.demo.domain.exception;

public class InsufficientStockException extends RuntimeException {

    // Constructor básico con mensaje
    public InsufficientStockException(String message) {
        super(message);
    }

    // Constructor detallado con información contextual
    public InsufficientStockException(Long productId, int available, int requested) {
        super(String.format(
                "Stock insuficiente para el producto %d. Disponible: %d, Solicitado: %d",
                productId, available, requested
        ));
    }
}