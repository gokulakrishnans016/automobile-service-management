package com.example.AutomobileApplication.controller;

import com.example.AutomobileApplication.dto.LoginRequestDTO;
import com.example.AutomobileApplication.dto.UserResponseDTO;
import com.example.AutomobileApplication.entity.User;
import com.example.AutomobileApplication.mapper.UserMapper;
import com.example.AutomobileApplication.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO dto) {
        try {
            User user = authService.login(dto.getUsername(), dto.getPassword());
            return ResponseEntity.ok(userMapper.toDTO(user));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}