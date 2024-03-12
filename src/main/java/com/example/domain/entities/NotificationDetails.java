package com.example.domain.entities;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * NotificationDetails encapsulates all necessary information required for sending a notification.
 */
public class NotificationDetails {

    private static final int MAX_SUBJECT_LENGTH = 100;
    private static final int MAX_BODY_LENGTH = 500;
    private static final int MIN_BODY_LENGTH = 50; // Assuming a minimum body length requirement
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; // Regex pattern for email validation

    private final EmailValidator emailValidator;
    private String recipientEmail;
    private String messageSubject;
    private String messageBody;
    private NotificationType notificationType;

    /**
     * Constructor for NotificationDetails with email validator.
     * @param emailValidator the EmailValidator to use for validating email addresses
     */
    public NotificationDetails(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    /**
     * Retrieves the recipient's email address.
     * @return the recipient email
     */
    public String getRecipientEmail() {
        return recipientEmail;
    }

    /**
     * Sets the recipient's email address after validation.
     * @param recipientEmail the email address to set
     * @throws IllegalArgumentException if the email is invalid or null
     */
    public void setRecipientEmail(String recipientEmail) {
        if (recipientEmail == null || !recipientEmail.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.recipientEmail = recipientEmail;
    }

    /**
     * Retrieves the subject of the message.
     * @return the message subject
     */
    public String getMessageSubject() {
        return messageSubject;
    }

    /**
     * Sets the subject of the message.
     * @param messageSubject the message subject to set
     * @throws IllegalArgumentException if the subject is invalid
     */
    public void setMessageSubject(String messageSubject) {
        if (messageSubject == null || messageSubject.isEmpty() || messageSubject.length() > MAX_SUBJECT_LENGTH) {
            throw new IllegalArgumentException("Invalid message subject");
        }
        this.messageSubject = messageSubject;
    }

    /**
     * Retrieves the body content of the message.
     * @return the message body
     */
    public String getMessageBody() {
        return messageBody;
    }

    /**
     * Sets the body content of the message.
     * @param messageBody the message body to set
     * @throws IllegalArgumentException if the body is invalid
     */
    public void setMessageBody(String messageBody) {
        if (messageBody == null || messageBody.isEmpty() || messageBody.length() > MAX_BODY_LENGTH || messageBody.length() < MIN_BODY_LENGTH) {
            throw new IllegalArgumentException("Invalid message body");
        }
        this.messageBody = messageBody;
    }

    /**
     * Retrieves the type of the notification.
     * @return the notification type
     */
    public NotificationType getNotificationType() {
        return notificationType;
    }

    /**
     * Sets the type of the notification.
     * @param notificationType the notification type to set
     * @throws IllegalArgumentException if the notification type is null
     */
    public void setNotificationType(NotificationType notificationType) {
        if (notificationType == null) {
            throw new IllegalArgumentException("Notification type cannot be null");
        }
        this.notificationType = notificationType;
    }
}

// TODO: Extract to its own file under package com.example.domain.entities
enum NotificationType {
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