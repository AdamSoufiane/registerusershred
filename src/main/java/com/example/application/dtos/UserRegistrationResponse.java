package com.example.application.dtos;

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

    private final String message;

    private final Long userId;
}
