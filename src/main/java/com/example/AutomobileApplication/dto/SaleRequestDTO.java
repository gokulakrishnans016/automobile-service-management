package com.example.AutomobileApplication.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleRequestDTO {

    @NotNull(message = "Vehicle ID is required")
    private Long vehicleId;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Sale price is required")
    @Positive(message = "Sale price must be positive")
    private Double salePrice;

    @PositiveOrZero(message = "Discount cannot be negative")
    private Double discount;

    @PositiveOrZero(message = "Tax cannot be negative")
    private Double taxAmount;

    private String status; // optional

    private LocalDate deliveryDate;
}