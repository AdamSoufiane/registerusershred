package com.example.application.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserActivationResponse that = (UserActivationResponse) o;
        return successful == that.successful &&
               Objects.equals(message, that.message) &&
               Objects.equals(createdDate, that.createdDate) &&
               Objects.equals(lastModifiedDate, that.lastModifiedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, successful, createdDate, lastModifiedDate);
    }

    @Override
    public String toString() {
        return "UserActivationResponse{" +
                "message='" + message + '\'' +
                ", successful=" + successful +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}