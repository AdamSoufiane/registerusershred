package com.example.domain.entities;

public enum NotificationType {
    EMAIL("Represents an email type notification. Used when notifying users via email."),
    SMS("Represents an SMS type notification. Used for sending text messages to users' mobile devices."),
    PUSH("Represents a push notification. Used for sending notifications directly to users' devices or applications.");

    private final String description;

    NotificationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}