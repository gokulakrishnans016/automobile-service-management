package com.example.AutomobileApplication.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleServiceRequestDTO {

    @NotNull(message = "Vehicle ID required")
    private Long vehicleId;

    @NotNull(message = "Customer ID required")
    private Long customerId;

    @NotEmpty(message = "At least one service must be selected")
    private List<String> serviceType;

    @NotBlank(message = "Service description required")
    private String serviceDescription;

    private Double serviceCost;

    private String status;

    private LocalDate nextServiceDate;
}