//package com.devon.contractmanagementsystem.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.devon.contractmanagementsystem.model.User;
//import com.devon.contractmanagementsystem.model.UserLoginRequest;
//
//import com.devon.contractmanagementsystem.service.UserService;
//
//@RestController
//@CrossOrigin
//
//public class UserController {
//
//    @Autowired
//    UserService userService;
//
//
//    @PostMapping("/signup")
//    public User signUp(@RequestBody User user) {
//        // Save user to the database
//        return userService.signUp(user);
//    }
//
//    @PostMapping("/login")
//    public User login(@RequestBody UserLoginRequest loginRequest) {
//        // Retrieve user from the database based on email and password
//        //return userService.login(user);
//        return userService.login(loginRequest.getEmail(),loginRequest.getPassword());
//    }
//
//    @GetMapping("/firstname")
//    public String getUserFirstName(@RequestParam("email") String email){
//        User user = userService.getUserByEmail(email);
//        if(user != null){
//            return user.getFirstName();
//        }
//        else{
//            return "";
//        }
//    }
//}

package com.devon.contractmanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.devon.contractmanagementsystem.model.User;
import com.devon.contractmanagementsystem.model.UserLoginRequest;
// import com.devon.contractmanagementsystem.repository.UserRepository;
import com.devon.contractmanagementsystem.service.UserService;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping("/signup")
    public User signUp(@RequestBody User user) {
        // Save user to the database
        return userService.signUp(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody UserLoginRequest loginRequest) {
        // Retrieve user from the database based on email and password
        //return userService.login(user);
        return userService.login(loginRequest.getEmail(),loginRequest.getPassword());
    }

    @GetMapping("/firstname")
    public String getUserFirstName(@RequestParam("email") String email){
        User user = userService.getUserByEmail(email);
        if(user != null){
            return user.getFirstName();
        }
        else{
            return "";
        }
    }
}
