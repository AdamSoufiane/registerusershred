package com.example.infrastructure.external_services;

import com.example.infrastructure.external_services.exceptions.EmailSendingException;

public class EmailServiceProvider {

    public void sendEmail(String recipient, String message) throws EmailSendingException {
        if (!isConfigured()) {
            throw new EmailSendingException("Email service provider is not configured.");
        }
        // Implementation for sending email
        System.out.println("Email sent to " + recipient + " with message: " + message);
    }

    public boolean isConfigured() {
        // Implementation to check if the service is configured
        // This is a dummy implementation for demonstration purposes
        return true;
    }
}
