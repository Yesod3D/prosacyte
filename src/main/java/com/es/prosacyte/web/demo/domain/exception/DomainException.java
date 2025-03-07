package com.es.prosacyte.web.demo.domain.exception;

/**
 * Base class for all domain-specific exceptions
 */
public class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}