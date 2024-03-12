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

    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> roles;
    private LocalDateTime lastLogin;
    private Long version;
    private String hashAlgorithm;
    private String salt;
    private UserStatus status;
    private boolean isActive;

    public boolean validate() {
        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
        Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$");

        if (this.email == null || !emailPattern.matcher(this.email).matches()) {
            return false;
        }
        if (this.password == null || !passwordPattern.matcher(this.password).matches()) {
            return false;
        }
        if (this.username == null || !usernamePattern.matcher(this.username).matches()) {
            return false;
        }
        return true;
    }

    public void activate() {
        if (this.status != UserStatus.ACTIVE) {
            this.status = UserStatus.ACTIVE;
            this.isActive = true;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void deactivate() {
        if (this.status != UserStatus.INACTIVE) {
            this.status = UserStatus.INACTIVE;
            this.isActive = false;
            this.updatedAt = LocalDateTime.now();
        }
    }

    public String hashPassword() {
        return hashPassword(this.password);
    }

    public boolean comparePassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            return false;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
