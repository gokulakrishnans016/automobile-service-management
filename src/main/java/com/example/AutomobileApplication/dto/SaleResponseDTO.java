package com.example.AutomobileApplication.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleResponseDTO {

    private Long id;
    private LocalDateTime saleDate;

    private String vehicleName;
    private String customerName;

    private Double salePrice;
    private Double discount;
    private Double taxAmount;
    private Double finalAmount;

    private String status;
    private LocalDate deliveryDate;
}