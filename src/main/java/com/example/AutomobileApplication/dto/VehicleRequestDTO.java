package com.example.AutomobileApplication.dto;

import jakarta.validation.constraints.*;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequestDTO {

    @NotBlank(message = "Vehicle name is required")
    private String vehicleName;

    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Model is required")
    private String model;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private Double price;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stockQuantity;

    @NotBlank(message = "Fuel type is required")
    private String fuelType;

    private String transmission;
    private String color;

    @Min(value = 1900, message = "Invalid manufacture year")
    private int manufactureYear;

    @NotBlank(message = "Registration number is required")
    private String registrationNumber;

    private String status; // optional
    private String customerName;
    private Long customerId;
}