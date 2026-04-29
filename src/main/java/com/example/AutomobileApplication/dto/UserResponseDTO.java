package com.example.AutomobileApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String role;
    private boolean active;
}