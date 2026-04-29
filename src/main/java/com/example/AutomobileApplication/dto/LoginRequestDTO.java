package com.example.AutomobileApplication.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String username; // username OR email
    private String password;
}