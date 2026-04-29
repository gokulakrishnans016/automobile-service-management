package com.example.AutomobileApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDTO {

    private Long id;

    private String vehicleName;
    private String brand;
    private String model;

    private Double price;
    private int stockQuantity;

    private String fuelType;
    private String transmission;

    private String color;
    private int manufactureYear;

    private String registrationNumber;

    private String status;

    private String customerName;
    private Long customerId;
}