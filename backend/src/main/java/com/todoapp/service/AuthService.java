package com.todoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.todoapp.dto.AuthResponse;
import com.todoapp.dto.LoginRequest;
import com.todoapp.dto.RegisterRequest;
import com.todoapp.dto.UserDto;
import com.todoapp.entity.User;
import com.todoapp.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

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

            // Generate JWT token
            UserDetails userDetails = userDetailsService.loadUserByUsername(savedUser.getEmail());
            String jwtToken = jwtService.generateToken(userDetails);

            return new AuthResponse(true, "User registered successfully", jwtToken, userDto);

        } catch (Exception e) {
            return new AuthResponse(false, "Registration failed: " + e.getMessage());
        }
    }

    public AuthResponse login(LoginRequest request) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
                )
            );

            // Find user by email
            User user = userRepository.findByEmail(request.getEmail()).orElse(null);
            
            if (user == null) {
                return new AuthResponse(false, "Invalid email or password");
            }

            // Create UserDto for response
            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());

            // Generate JWT token
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            String jwtToken = jwtService.generateToken(userDetails);

            return new AuthResponse(true, "Login successful", jwtToken, userDto);

        } catch (Exception e) {
            return new AuthResponse(false, "Login failed: Invalid email or password");
        }
    }
}