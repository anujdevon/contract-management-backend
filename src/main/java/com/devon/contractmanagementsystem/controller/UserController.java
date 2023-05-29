package com.devon.contractmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.devon.contractmanagementsystem.model.User;
// import com.devon.contractmanagementsystem.repository.UserRepository;
import com.devon.contractmanagementsystem.service.UserService;

@RestController
public class UserController {
    // private final UserRepository userRepository;

    // @Autowired
    // public UserController(UserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }

    // @PostMapping("/signup")
    // public User signUp(@RequestBody User user) {
    //     // Save user to the database
    //     return userRepository.save(user);
    // }
        @Autowired
        UserService userService;
    @PostMapping("/login")
    public User login(@RequestBody User user) {
        // Retrieve user from the database based on email and password
        return userService.login(user);
    }
}
