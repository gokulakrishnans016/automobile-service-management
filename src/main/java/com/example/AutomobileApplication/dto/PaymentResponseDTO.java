package com.example.AutomobileApplication.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseDTO {

    private Long id;
    private LocalDateTime paymentDate;

    private String vehicleName;
    private String customerName;

    private Double amount;
    private String paymentMethod;
    private String paymentStatus;
    private String transactionId;

    private Long saleId;
    private Double totalAmount;
    private Double paidAmount;
    private Double remainingAmount;
}