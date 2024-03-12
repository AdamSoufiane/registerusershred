package com.example.application.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.io.Serializable;

/**
 * UserRegistrationResponse is a DTO that encapsulates the response data after successful user registration,
 * including information like a message and the user ID.
 */
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class UserRegistrationResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("userId")
    private final Long userId;
}
