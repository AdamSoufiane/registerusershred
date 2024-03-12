package com.example.domain.exceptions;

import java.io.Serializable;
import lombok.extern.slf4j.Slf4j;
import java.lang.RuntimeException;

@Slf4j
public class UserRepositoryException extends RuntimeException implements Serializable {

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