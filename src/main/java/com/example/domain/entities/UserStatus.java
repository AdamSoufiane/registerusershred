package com.example.domain.entities;

public enum UserStatus {
    ACTIVE("Indicates that the user's account is currently active."),
    INACTIVE("Indicates that the user's account is not active and may require activation or reactivation."),
    PENDING_ACTIVATION("Represents the state of a user's account that has been registered but not yet activated.");

    private final String description;

    UserStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}