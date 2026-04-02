package com.example.auth.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("users")
public class User {

    @PrimaryKey
    private String email;

    private UUID userId;
    private String passwordHash;
    private String fullName;
    private String phone;
    private Instant createdAt;

    public User() {}

    public User(String email, UUID userId, String passwordHash, String fullName, String phone) {
        this.email = email;
        this.userId = userId;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.phone = phone != null ? phone : "";
        this.createdAt = Instant.now();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
