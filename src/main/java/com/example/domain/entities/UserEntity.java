package com.example.domain.entities;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Getter
@Setter
public class UserEntity {

    public enum UserStatus {
        ACTIVE,
        INACTIVE,
        PENDING_ACTIVATION
    }

    private Long id;
    private String username;
    private String password;
    private String email;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> roles;
    private LocalDateTime lastLogin;
    private Long version;
    private String hashAlgorithm;
    private String salt;
    private UserStatus status;

    public boolean validate() {
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
        Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");

        if (this.email == null || !emailPattern.matcher(this.email).matches()) {
            throw new IllegalArgumentException("Invalid or missing email format");
        }
        if (this.password == null || !passwordPattern.matcher(this.password).matches()) {
            throw new IllegalArgumentException("Invalid or missing password complexity");
        }
        if (!usernamePattern.matcher(this.username).matches()) {
            throw new IllegalArgumentException("Invalid or missing username");
        }
        return true;
    }

    public void activate() {
        if (this.status != UserStatus.ACTIVE) {
            this.isActive = true;
            this.status = UserStatus.ACTIVE;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void deactivate() {
        if (this.status != UserStatus.INACTIVE) {
            this.isActive = false;
            this.status = UserStatus.INACTIVE;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public String hashPassword() {
        return hashPassword(this.password);
    }

    public boolean comparePassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Plain password cannot be null or empty");
        }
        String hashedPlainPassword = hashPassword(plainPassword);
        return MessageDigest.isEqual(this.password.getBytes(), hashedPlainPassword.getBytes());
    }

    private String hashPassword(String plainPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance(this.hashAlgorithm);
            md.update((this.salt + plainPassword).getBytes());
            byte[] hashedBytes = md.digest();
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Invalid hash algorithm");
        }
    }

    // Additional methods and logic as per business requirements

}