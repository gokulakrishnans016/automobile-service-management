package com.example.AutomobileApplication.config;

import com.example.AutomobileApplication.entity.User;
import com.example.AutomobileApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public void run(String... args)
    {

        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();

            admin.setUsername("admin");
            admin.setEmail("admin@gmail.com");

            admin.setPassword(passwordEncoder.encode("admin123"));

            admin.setRole("ADMIN");

            userRepository.save(admin);

            System.out.println(" ADMIN CREATED SUCCESSFULLY...");
        }
    }
}