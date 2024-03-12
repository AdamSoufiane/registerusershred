package com.example.domain.entities;

import lombok.Getter;
import lombok.Setter;

/**
 * NotificationDetails encapsulates all necessary information required for sending a notification.
 */
@Getter
@Setter
public class NotificationDetails {

    private static final int MAX_SUBJECT_LENGTH = 100;
    private static final int MAX_BODY_LENGTH = 500;
    private static final int MIN_BODY_LENGTH = 50; // Assuming a minimum body length requirement
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; // Regex pattern for email validation

    private String recipientEmail;
    private String messageSubject;
    private String messageBody;
    private NotificationType notificationType;

    public void setRecipientEmail(String recipientEmail) {
        if (recipientEmail == null || !recipientEmail.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.recipientEmail = recipientEmail.toLowerCase();
    }

    public void setMessageSubject(String messageSubject) {
        if (messageSubject == null || messageSubject.isEmpty() || messageSubject.length() > MAX_SUBJECT_LENGTH) {
            throw new IllegalArgumentException("Invalid message subject");
        }
        this.messageSubject = messageSubject;
    }

    public void setMessageBody(String messageBody) {
        if (messageBody == null || messageBody.isEmpty() || messageBody.length() > MAX_BODY_LENGTH || messageBody.length() < MIN_BODY_LENGTH) {
            throw new IllegalArgumentException("Invalid message body");
        }
        this.messageBody = messageBody;
    }

    public void setNotificationType(NotificationType notificationType) {
        if (notificationType == null) {
            throw new IllegalArgumentException("Notification type cannot be null");
        }
        this.notificationType = notificationType;
    }
}
