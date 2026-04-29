package com.example.AutomobileApplication.service;

import com.example.AutomobileApplication.entity.User;
import com.example.AutomobileApplication.exception.ResourceNotFoundException;
import com.example.AutomobileApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User login(String input, String password) {

        // Find by username OR email
        User user = userRepository.findByUsername(input)
                .orElseGet(() -> userRepository.findByEmail(input)
                .orElseThrow(() -> new ResourceNotFoundException("User not found")));

        //  Password check
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResourceNotFoundException("Password Incorrect");
        }

        if (!user.isActive()) {
            throw new ResourceNotFoundException("User account is inactive");
        }

        return user;
    }
}