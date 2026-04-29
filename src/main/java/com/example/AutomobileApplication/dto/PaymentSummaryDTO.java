package com.example.AutomobileApplication.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSummaryDTO {

    private Long saleId;
    private Long customerId;

    private String vehicleName;
    private String customerName;

    private Double totalAmount;
    private Double paidAmount;
    private Double remainingAmount;

    private int paymentCount;
    private LocalDateTime lastPaymentDate;

    private List<PaymentItemDTO> payments;
}