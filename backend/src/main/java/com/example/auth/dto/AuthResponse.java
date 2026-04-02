package com.example.auth.dto;

public class AuthResponse {

    private boolean success;
    private String message;
    private UserProfile user;

    public AuthResponse() {}

    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public AuthResponse(boolean success, String message, UserProfile user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }

    public static AuthResponse success(String message, UserProfile user) {
        return new AuthResponse(true, message, user);
    }

    public static AuthResponse failure(String message) {
        return new AuthResponse(false, message);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserProfile getUser() {
        return user;
    }

    public void setUser(UserProfile user) {
        this.user = user;
    }
}
