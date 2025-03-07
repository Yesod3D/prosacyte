package com.es.prosacyte.web.demo.domain.exception;

public class ProductAlreadyInCategoryException extends RuntimeException {

    public ProductAlreadyInCategoryException(Long productId, Long categoryId) {
        super(String.format(
                "El producto con ID %d ya está asociado a la categoría %d",
                productId,
                categoryId
        ));
    }

    public ProductAlreadyInCategoryException(String message) {
        super(message);
    }
}
