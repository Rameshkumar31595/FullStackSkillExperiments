package com.klu.service;

import com.klu.entity.User;
import com.klu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User save(User u){
        return repo.save(u);
    }

    public User find(String username){
        return repo.findByUsername(username);
    }

    public boolean exists(String username) {
        return repo.existsByUsername(username);
    }
}