package com.example.incidentmanagement.service;

import com.example.incidentmanagement.model.Role;
import com.example.incidentmanagement.model.User;
import com.example.incidentmanagement.repository.UserRepository;
import com.example.incidentmanagement.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired private UserRepository userRepository;
    @Autowired private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        User user = new User(null, username, passwordEncoder.encode(password), Role.USER);
        userRepository.save(user);
        return jwtUtil.generateToken(username);
    }

    public String loginUser(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty() || !passwordEncoder.matches(password, userOptional.get().getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(username);
    }
}
