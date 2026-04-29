package com.example.AutomobileApplication.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    private String username;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Minimum 6 characters")
    private String password;

    private String role; // OPTIONAL
}