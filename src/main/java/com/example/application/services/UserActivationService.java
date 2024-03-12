package com.example.application.services;

import com.example.application.dtos.UserActivationRequest;
import com.example.application.dtos.UserActivationResponse;
import com.example.domain.entities.NotificationDetails;
import com.example.domain.entities.NotificationType;
import com.example.domain.entities.UserEntity;
import com.example.domain.exceptions.NotificationException;
import com.example.domain.exceptions.UserRepositoryException;
import com.example.domain.ports.NotificationOutputPort;
import com.example.domain.ports.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserActivationService {

    private final UserRepositoryPort userRepositoryPort;
    private final NotificationOutputPort notificationOutputPort;

    public UserActivationResponse activateUser(UserActivationRequest request) throws UserRepositoryException {
        Optional<UserEntity> userOptional = userRepositoryPort.findByActivationToken(request.getActivationToken());
        if (!userOptional.isPresent()) {
            return new UserActivationResponse("Activation token is invalid.", false);
        }

        UserEntity user = userOptional.get();
        if (user.isActive()) {
            return new UserActivationResponse("User is already active.", false);
        }
        user.setActive(true);
        userRepositoryPort.save(user);

        try {
            NotificationDetails details = new NotificationDetails();
            details.setRecipientEmail(user.getEmail());
            details.setMessageSubject("Account Activation");
            details.setMessageBody("Your account has been activated.");
            details.setNotificationType(NotificationType.EMAIL);
            notificationOutputPort.sendNotification(details);
        } catch (NotificationException e) {
            throw new UserRepositoryException("Failed to send activation notification.", e);
        }

        return new UserActivationResponse("User activated successfully.", true);
    }
}
