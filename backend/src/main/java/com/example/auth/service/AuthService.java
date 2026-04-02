package com.example.auth.service;

import com.example.auth.dto.AuthResponse;
import com.example.auth.dto.LoginRequest;
import com.example.auth.dto.SignupRequest;
import com.example.auth.dto.UserProfile;
import com.example.auth.model.User;
import com.example.auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse signup(SignupRequest request) {
        String email = request.getEmail().toLowerCase().trim();

        if (userRepository.existsById(email)) {
            return AuthResponse.failure("Email already registered");
        }

        String passwordHash = passwordEncoder.encode(request.getPassword());
        User user = new User(
                email,
                UUID.randomUUID(),
                passwordHash,
                request.getFullName().trim(),
                request.getPhone() != null ? request.getPhone().trim() : ""
        );

        userRepository.save(user);

        UserProfile profile = new UserProfile(
                user.getEmail(),
                user.getFullName(),
                user.getPhone()
        );

        return AuthResponse.success("Signup successful", profile);
    }

    public AuthResponse login(LoginRequest request) {
        String email = request.getEmail().toLowerCase().trim();

        Optional<User> optUser = userRepository.findById(email);
        if (optUser.isEmpty()) {
            return AuthResponse.failure("Invalid email or password");
        }

        User user = optUser.get();
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            return AuthResponse.failure("Invalid email or password");
        }

        UserProfile profile = new UserProfile(
                user.getEmail(),
                user.getFullName(),
                user.getPhone()
        );

        return AuthResponse.success("Login successful", profile);
    }
}
