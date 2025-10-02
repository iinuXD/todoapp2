package com.todoapp.service;

import com.todoapp.dto.AuthResponse;
import com.todoapp.dto.LoginRequest;
import com.todoapp.dto.RegisterRequest;
import com.todoapp.dto.UserDto;
import com.todoapp.entity.User;
import com.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest request) {
        try {
            // Check if user already exists
            if (userRepository.existsByEmail(request.getEmail())) {
                return new AuthResponse(false, "User with this email already exists");
            }

            // Create new user
            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

            User savedUser = userRepository.save(user);

            // Create UserDto for response
            UserDto userDto = new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail());

            // For now, return without JWT token - will add JWT later
            return new AuthResponse(true, "User registered successfully", "mock-jwt-token", userDto);

        } catch (Exception e) {
            return new AuthResponse(false, "Registration failed: " + e.getMessage());
        }
    }

    public AuthResponse login(LoginRequest request) {
        try {
            // Find user by email
            User user = userRepository.findByEmail(request.getEmail()).orElse(null);
            
            if (user == null) {
                return new AuthResponse(false, "Invalid email or password");
            }

            // Check password
            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return new AuthResponse(false, "Invalid email or password");
            }

            // Create UserDto for response
            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());

            // For now, return without JWT token - will add JWT later
            return new AuthResponse(true, "Login successful", "mock-jwt-token", userDto);

        } catch (Exception e) {
            return new AuthResponse(false, "Login failed: " + e.getMessage());
        }
    }
}