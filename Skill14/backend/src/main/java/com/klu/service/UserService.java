package com.klu.service;

import com.klu.entity.User;
import com.klu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public User register(User u){
        return repo.save(u);
    }

    public User login(String username,String password){
        return repo.findByUsernameAndPassword(username,password);
    }

    public User getUser(String username){
        return repo.findByUsername(username);
    }
}