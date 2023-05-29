package com.devon.contractmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devon.contractmanagementsystem.model.User;
import com.devon.contractmanagementsystem.repository.UserRepository;

@Service
public class UserService {
    @Autowired
        UserRepository userRepository;
    public User login(User user) {
        
        //Retrieve user from the database based on email and password
        return userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        //return new User("anuj","abc");
    }
    
}
