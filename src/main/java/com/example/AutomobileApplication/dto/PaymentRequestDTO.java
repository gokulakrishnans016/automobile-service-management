package com.example.AutomobileApplication.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {

    @NotNull(message = "Sale ID is required")
    private Long saleId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Payment method is required")
    private String paymentMethod; // CASH / CARD / UPI

    private String paymentStatus; // optional

    private String transactionId;
}