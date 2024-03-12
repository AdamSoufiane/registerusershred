package com.example.domain.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationException extends Exception {

    private static final long serialVersionUID = 1L;
    private String errorCode;
    private long timestamp;

    public NotificationException() {
        super();
        this.timestamp = System.currentTimeMillis();
    }

    public NotificationException(String message) {
        super(message);
        this.timestamp = System.currentTimeMillis();
    }

    public NotificationException(String message, Throwable cause) {
        super(message, cause);
        this.timestamp = System.currentTimeMillis();
    }

    public NotificationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.timestamp = System.currentTimeMillis();
    }

    public NotificationException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "NotificationException{" +
                "errorCode='" + errorCode + '\'' +
                ", timestamp=" + timestamp +
                ", message='" + getMessage() + '\'' +
                ", cause=" + getCause() +
                '}';
    }
}
