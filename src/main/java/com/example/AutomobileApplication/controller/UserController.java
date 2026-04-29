package com.example.AutomobileApplication.controller;

import com.example.AutomobileApplication.dto.UserRequestDTO;
import com.example.AutomobileApplication.dto.UserResponseDTO;
import com.example.AutomobileApplication.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER
    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody UserRequestDTO dto) {
        return userService.register(dto);
    }

    // GET ALL
    @GetMapping
    public List<UserResponseDTO> getAll() {
        return userService.getAllUsers();
    }
}