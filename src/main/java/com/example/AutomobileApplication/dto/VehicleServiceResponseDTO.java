package com.example.AutomobileApplication.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleServiceResponseDTO {

    private Long id;

    private LocalDate serviceDate;

    private String serviceType;
    private String serviceDescription;
    private Double serviceCost;

    private String status;
    private LocalDate nextServiceDate;

    private String vehicleName;
    private String customerName;
}