package com.example.application.services;

import com.example.application.dtos.UserRegistrationRequest;
import com.example.application.dtos.UserRegistrationResponse;
import com.example.domain.entities.UserEntity;
import com.example.domain.exceptions.UserRegistrationException;
import com.example.domain.ports.UserRepositoryPort;
import com.example.application.ports.NotificationOutputPort;
import com.example.domain.entities.UserEntityFactory;
import com.example.domain.entities.NotificationDetails;
import com.example.domain.exceptions.NotificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Service
public class UserRegistrationService {

    private final UserRepositoryPort userRepositoryPort;
    private final NotificationOutputPort notificationOutputPort;
    private final UserEntityFactory userEntityFactory;
    private final Validator validator;

    @Autowired
    public UserRegistrationService(UserRepositoryPort userRepositoryPort,
                                   NotificationOutputPort notificationOutputPort,
                                   UserEntityFactory userEntityFactory,
                                   Validator validator) {
        this.userRepositoryPort = userRepositoryPort;
        this.notificationOutputPort = notificationOutputPort;
        this.userEntityFactory = userEntityFactory;
        this.validator = validator;
    }

    @Transactional
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) throws UserRegistrationException {
        try {
            // Validate the request parameters
            Set<ConstraintViolation<UserRegistrationRequest>> violations = validator.validate(request);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }

            // Create UserEntity using the factory
            UserEntity userEntity = userEntityFactory.createUser(request.getEmail(), request.getPassword(), request.getFullName());

            // Save the user entity
            UserEntity savedUser = userRepositoryPort.save(userEntity);

            // Send out a notification
            NotificationDetails details = new NotificationDetails(savedUser.getEmail(), "Welcome to our service!", NotificationDetails.NotificationType.REGISTRATION_CONFIRMATION);
            notificationOutputPort.sendNotification(details);

            // Construct and return the response
            return new UserRegistrationResponse("User successfully registered.", savedUser.getId());
        } catch (ConstraintViolationException ex) {
            throw new UserRegistrationException("Validation failed: " + ex.getConstraintViolations().toString());
        } catch (NotificationException ex) {
            throw new UserRegistrationException("Failed to send registration confirmation email.", ex);
        }
    }
}
