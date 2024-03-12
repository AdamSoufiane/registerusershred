package com.example.application.dtos;

import lombok.Data;

/**
 * UserActivationRequest is a DTO for transferring the activation token from the presentation layer to the application service.
 */
@Data
public class UserActivationRequest {

    /**
     * The token used to activate a user's account.
     */
    private String activationToken;

}