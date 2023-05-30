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
        User resultUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        //Retrieve user from the database based on email and password
        return resultUser;
        //return new User("anuj","abc");
    }
    public User signUp(User user)
    {
        return userRepository.save(user);
    }

    
}
