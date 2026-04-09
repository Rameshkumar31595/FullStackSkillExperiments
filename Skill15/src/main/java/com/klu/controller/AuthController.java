package com.klu.controller;

import com.klu.entity.User;
import com.klu.jwt.JwtUtil;
import com.klu.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwt;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){

        User u = service.find(user.getUsername());

        if(u!=null && u.getPassword().equals(user.getPassword())){
            String token = jwt.generateToken(u.getUsername(),u.getRole());
            return ResponseEntity.ok(Map.of("token", token));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid username or password"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (user.getUsername() == null || user.getPassword() == null || user.getRole() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "username, password, role are required"));
        }

        if (service.exists(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", "Username already exists"));
        }

        user.setRole(user.getRole().toUpperCase());
        User saved = service.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "id", saved.getId(),
                "username", saved.getUsername(),
                "role", saved.getRole()
        ));
    }
}