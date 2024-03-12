package com.example.infrastructure.external_services;

import com.example.application.ports.NotificationOutputPort;
import com.example.domain.entities.NotificationDetails;
import com.example.domain.exceptions.NotificationException;
import com.example.infrastructure.external_services.exceptions.EmailSendingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceAdapter implements NotificationOutputPort {

    private final EmailServiceProvider emailServiceProvider;
    private final int maxRetryAttempts = 3;

    public EmailServiceAdapter(EmailServiceProvider emailServiceProvider) {
        this.emailServiceProvider = emailServiceProvider;
    }

    @Override
    public void sendNotification(NotificationDetails details) throws NotificationException {
        try {
            validateEmailServiceProvider();
            emailServiceProvider.sendEmail(details.getRecipient(), details.getMessage());
            handleSuccessfulEmailDelivery(details.getRecipient(), details.getMessage());
        } catch (EmailSendingException e) {
            log.error("Failed to send email to {} with message: {}. Error: ", details.getRecipient(), details.getMessage(), e);
            boolean retrySuccessful = retrySendingEmail(details.getRecipient(), details.getMessage());
            if (!retrySuccessful) {
                handleRetryFailure(details.getRecipient(), details.getMessage());
                throw new NotificationException("Failed to send email notification after retries", e);
            }
        }
    }

    private void validateEmailServiceProvider() throws EmailSendingException {
        if (emailServiceProvider == null || !emailServiceProvider.isConfigured()) {
            throw new EmailSendingException("Email service provider is not initialized or properly configured.");
        }
    }

    private void handleSuccessfulEmailDelivery(String recipient, String message) {
        // Implementation to handle successful email delivery
    }

    private void logEmailSendingError(Exception e, String recipient, String message) {
        log.error("Failed to send email to {} with message: {}. Error: ", recipient, message, e);
    }

    private boolean retrySendingEmail(String recipient, String message) {
        for (int i = 0; i < maxRetryAttempts; i++) {
            try {
                Thread.sleep(1000); // Delay of 1 second between retries
                emailServiceProvider.sendEmail(recipient, message);
                return true;
            } catch (InterruptedException ex) {
                log.error("Email sending interrupted on retry {} for recipient {}", i, recipient);
                Thread.currentThread().interrupt(); // Set the interrupt flag again
                return false; // Exit the retry loop due to interruption
            } catch (EmailSendingException e) {
                log.error("Retry {} for {} failed", i + 1, recipient);
            }
        }
        return false;
    }

    private void handleRetryFailure(String recipient, String message) {
        log.error("All retry attempts to send email to {} with message '{}' have failed.", recipient, message);
        // TODO: Implement notification to an operations team or incident management system for failures that exceed the maximum retry attempts
    }
}
