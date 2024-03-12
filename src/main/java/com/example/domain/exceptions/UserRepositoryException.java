package com.example.domain.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserRepositoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserRepositoryException(String message) {
        super(message);
        log.error(message);
    }

    public UserRepositoryException(String message, Throwable cause) {
        super(message, cause);
        log.error(message, cause);
    }
}