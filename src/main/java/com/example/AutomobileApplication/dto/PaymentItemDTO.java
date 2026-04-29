package com.example.AutomobileApplication.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentItemDTO {

    private Double amount;
    private String method;
    private String status;
    private String transactionId;
    private LocalDateTime date;
}