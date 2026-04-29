package com.example.AutomobileApplication.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {

    private Long id;

    private String name;
    private Integer age;
    private LocalDate dateOfBirth;
    private String gender;

    private String email;
    private String phone;

    private String address;
    private String city;
    private String state;
    private String pincode;

    private String drivingLicenseNumber;

    private LocalDateTime createdAt;
}