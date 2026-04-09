package com.klu.controller;

import com.klu.entity.User;
import com.klu.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins="*")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User u){
        return service.register(u);
    }

    @PostMapping("/login")
    public User login(@RequestBody User u){
        return service.login(u.getUsername(),u.getPassword());
    }

    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username){
        return service.getUser(username);
    }
}