package com.es.prosacyte.web.demo.domain.exception;

public class ProductNotInCategoryException extends RuntimeException {

    // Constructor principal con IDs específicos
    public ProductNotInCategoryException(Long productId, Long categoryId) {
        super(String.format(
                "El producto con ID %d no está asociado a la categoría %d",
                productId,
                categoryId
        ));
    }

    // Constructor alternativo para mensajes personalizados
    public ProductNotInCategoryException(String message) {
        super(message);
    }
}