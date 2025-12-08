package com.ahmed.backend.service;

import com.ahmed.backend.model.User;
import com.ahmed.backend.payload.request.LoginRequest;
import com.ahmed.backend.payload.request.RegisterRequest;
import com.ahmed.backend.payload.response.JwtResponse;
import com.ahmed.backend.payload.response.MessageResponse;
import com.ahmed.backend.repository.UserRepository;
import com.ahmed.backend.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public MessageResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        return new MessageResponse("User registered successfully");
    }

    public JwtResponse authenticate(LoginRequest request) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String jwt = jwtService.generateToken(principal);
        return new JwtResponse(jwt);
    }
}

