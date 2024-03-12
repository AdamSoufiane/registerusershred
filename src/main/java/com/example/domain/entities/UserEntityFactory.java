package com.example.domain.entities;

import com.example.domain.exceptions.InvalidUserDetailsException;

import java.time.LocalDateTime;

public class UserEntityFactory implements IUserEntityFactory {

    @Override
    public UserEntity createUser(String username, String email, String password) throws InvalidUserDetailsException {
        if (username == null || username.trim().isEmpty()) {
            throw new InvalidUserDetailsException("Username cannot be empty");
        }
        if (email == null || !email.contains("@")) {
            throw new InvalidUserDetailsException("Invalid email format");
        }
        if (password == null || password.length() < 6) {
            throw new InvalidUserDetailsException("Password must be at least 6 characters long");
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setStatus(UserStatus.PENDING_ACTIVATION);
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity.setVersion(0L);
        userEntity.setHashAlgorithm("SHA-256");
        userEntity.setSalt("");

        if (!userEntity.validate()) {
            throw new InvalidUserDetailsException("User validation failed");
        }

        userEntity.setPassword(userEntity.hashPassword());

        return userEntity;
    }
}
