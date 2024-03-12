package com.example.application.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@ToString
public class UserActivationResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;
    private boolean successful;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public UserActivationResponse(String message, boolean successful) {
        this.message = message;
        this.successful = successful;
        this.createdDate = LocalDateTime.now();
        this.lastModifiedDate = this.createdDate;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

}