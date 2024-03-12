package com.example.adapters.primary;

import com.example.application.dtos.UserActivationRequest;
import com.example.application.dtos.UserActivationResponse;
import com.example.application.dtos.UserRegistrationRequest;
import com.example.application.dtos.UserRegistrationResponse;
import com.example.application.services.UserActivationService;
import com.example.application.services.UserRegistrationService;
import com.example.domain.exceptions.UserRegistrationException;
import com.example.domain.exceptions.UserRepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserRegistrationService userRegistrationService;

    @Autowired
    private UserActivationService userActivationService;

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserRegistrationRequest registrationRequest) {
        try {
            UserRegistrationResponse response = userRegistrationService.registerUser(registrationRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (UserRegistrationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserRegistrationResponse(e.getMessage()));
        }
    }

    @PostMapping(value = "/activate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> activateUser(@Valid @RequestBody UserActivationRequest activationRequest) {
        try {
            UserActivationResponse response = userActivationService.activateUser(activationRequest);
            return ResponseEntity.ok(response);
        } catch (UserRepositoryException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserActivationResponse(e.getMessage(), false));
        }
    }
}

// TODO: Implement the constructor UserRegistrationResponse(String message) in the UserRegistrationResponse class within the package com.example.application.dtos
// TODO: Implement the constructor UserActivationResponse(String message, boolean successful) in the UserActivationResponse class within the package com.example.application.dtos
