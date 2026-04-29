package com.example.AutomobileApplication.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {

    @NotBlank(message = "Customer name is required")
    private String name;

    @Min(value = 18, message = "Age must be at least 18")
    private Integer age;

    private LocalDate dateOfBirth;

    private String gender;

    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    private String state;

    @Pattern(regexp = "\\d{6}", message = "Pincode must be 6 digits")
    private String pincode;

    @NotBlank(message = "Driving license number required")
    private String drivingLicenseNumber;
}