package com.devon.contractmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devon.contractmanagementsystem.model.User;
import com.devon.contractmanagementsystem.repository.UserRepository;

//import org.springframework.security.crypto.bcrypt.BCrypt;

// @Service
// public class UserService {
//     @Autowired
//         UserRepository userRepository;
//     public User login(User user) {
//         User resultUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
//         //Retrieve user from the database based on email and password
//         return resultUser;
//         //return new User("anuj","abc");
//     }
//     public User signUp(User user)
//     {
//         return userRepository.save(user);
//     }


// }

// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User signUp(User user) {
        validateUser(user);
        //hashPassword(user);
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
//        if (user != null && verifyPassword(password, user.getPassword())) {
           return user;
//        }
//        return null;
    }

    private void validateUser(User user) {
        if (!StringUtils.hasText(user.getEmail()) ||
                !StringUtils.hasText(user.getPhoneNumber()) ||
                !StringUtils.hasText(user.getPassword())) {
            throw new IllegalArgumentException("Required fields are missing");
        }

        // Check if email or phone number is already registered
        User existingUserByEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserByEmail != null) {
            throw new IllegalArgumentException("Email is already registered");
        }

        User existingUserByPhoneNumber = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (existingUserByPhoneNumber != null) {
            throw new IllegalArgumentException("Phone number is already registered");
        }
    }

//    private void hashPassword(User user) {
//        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
//        user.setPassword(hashedPassword);
//    }
//
//private boolean verifyPassword(String password, String hashedPassword)
//{
//    return BCrypt.checkpw(password, hashedPassword);
//}
}
