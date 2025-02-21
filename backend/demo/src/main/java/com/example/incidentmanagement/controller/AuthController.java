package com.example.incidentmanagement.controller;

import com.example.incidentmanagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired private AuthService authService;

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> request) {
        String token = authService.registerUser(request.get("username"), request.get("password"));
        return Map.of("token", token);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String token = authService.loginUser(request.get("username"), request.get("password"));
        return Map.of("token", token);
    }
}
