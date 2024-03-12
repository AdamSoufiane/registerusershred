package com.example.adapters.primary;

import com.example.application.dtos.UserActivationRequest;
import com.example.application.dtos.UserActivationResponse;
import com.example.application.dtos.UserRegistrationRequest;
import com.example.application.dtos.UserRegistrationResponse;
import com.example.application.services.UserActivationService;
import com.example.application.services.UserRegistrationService;
import com.example.domain.exceptions.UserRegistrationException;
import com.example.domain.exceptions.UserActivationException;
import com.example.domain.exceptions.UserRepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final UserActivationService userActivationService;

    @Autowired
    public UserController(UserRegistrationService userRegistrationService, UserActivationService userActivationService) {
        this.userRegistrationService = userRegistrationService;
        this.userActivationService = userActivationService;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserRegistrationResponse> registerUser(@Valid @RequestBody UserRegistrationRequest registrationRequest) {
        try {
            UserRegistrationResponse response = userRegistrationService.registerUser(registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UserRegistrationException e) {
            return ResponseEntity.badRequest().body(new UserRegistrationResponse(e.getMessage(), null));
        }
    }

    @PostMapping(value = "/activate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserActivationResponse> activateUser(@Valid @RequestBody UserActivationRequest activationRequest) {
        try {
            UserActivationResponse response = userActivationService.activateUser(activationRequest);
            return ResponseEntity.ok(response);
        } catch (UserActivationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserActivationResponse(e.getMessage(), false));
        }
    }

    @ExceptionHandler({UserRegistrationException.class, UserActivationException.class})
    public ResponseEntity<String> handleBadRequestExceptions(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(UserRepositoryException.class)
    public ResponseEntity<String> handleUserRepositoryExceptions(UserRepositoryException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
