package com.example.domain.entities;

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

        UserEntity userEntity = new UserEntity(
            null, // id is null for a new entity
            username,
            password, // This should be hashed in the UserEntity constructor or similar
            email,
            false, // isActive is false by default
            LocalDateTime.now(), // createdAt
            LocalDateTime.now(), // updatedAt
            null, // roles are set to null by default
            null, // lastLogin is null by default
            0L, // version for optimistic locking set to 0 by default
            "SHA-256", // default hashing algorithm
            "", // salt is empty by default
            UserStatus.PENDING_ACTIVATION
        );

        if (!userEntity.validate()) {
            throw new InvalidUserDetailsException("User validation failed");
        }

        userEntity.setPassword(userEntity.hashPassword());

        return userEntity;
    }
}

// TODO: Ensure UserEntity has all the necessary methods and fields as described in its file dependencies
// TODO: Extract UserEntityFactory to its own file in the package com.example.domain.entities
