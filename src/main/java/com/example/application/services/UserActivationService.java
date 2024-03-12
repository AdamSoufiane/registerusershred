package com.example.application.services;

import com.example.application.dtos.UserActivationRequest;
import com.example.application.dtos.UserActivationResponse;
import com.example.domain.entities.NotificationDetails;
import com.example.domain.entities.UserEntity;
import com.example.domain.exceptions.NotificationException;
import com.example.domain.exceptions.UserRepositoryException;
import com.example.domain.ports.NotificationOutputPort;
import com.example.domain.ports.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserActivationService {

    private final UserRepositoryPort userRepositoryPort;
    private final NotificationOutputPort notificationOutputPort;

    public UserActivationResponse activateUser(UserActivationRequest request) throws UserRepositoryException {
        // Assuming findByActivationToken is defined in UserRepositoryPort
        Optional<UserEntity> userOptional = userRepositoryPort.findByActivationToken(request.getActivationToken());
        if (!userOptional.isPresent()) {
            return new UserActivationResponse("Activation token is invalid.", false, LocalDateTime.now(), LocalDateTime.now());
        }

        UserEntity user = userOptional.get();
        // Assuming UserEntity has setActive method
        user.setActive(true);
        userRepositoryPort.save(user);

        try {
            // Assuming NotificationDetails constructor exists
            NotificationDetails details = new NotificationDetails(user.getEmail(), "Account Activation", "Your account has been activated.");
            notificationOutputPort.sendNotification(details);
        } catch (NotificationException e) {
            throw new UserRepositoryException("Failed to send activation notification.", e);
        }

        return new UserActivationResponse("User activated successfully.", true, LocalDateTime.now(), LocalDateTime.now());
    }
}
