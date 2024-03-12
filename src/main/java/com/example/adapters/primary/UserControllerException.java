package com.example.adapters.primary;

public class UserControllerException extends RuntimeException {

    private final boolean clientError;

    public UserControllerException(String message, boolean clientError) {
        super(message);
        this.clientError = clientError;
    }

    public boolean isClientError() {
        return clientError;
    }
}
