package com.example.AutomobileApplication.mapper;

import com.example.AutomobileApplication.dto.PaymentResponseDTO;
import com.example.AutomobileApplication.entity.Payment;
import com.example.AutomobileApplication.entity.Sale;
import com.example.AutomobileApplication.repository.PaymentRepository;

public class PaymentMapper {

    public static PaymentResponseDTO toDTO(Payment payment, PaymentRepository paymentRepository) {

        if (payment == null) return null;

        Sale sale = payment.getSale();

        String vehicleName = "N/A";
        String customerName = "N/A";
        Long saleId = null;

        double totalAmount = 0;
        double paidAmount = 0;
        double remainingAmount = 0;

        if (sale != null)
        {
            saleId = sale.getId();

            if (sale.getVehicle() != null)
            {
                vehicleName = sale.getVehicle().getVehicleName();
            }

            if (sale.getCustomer() != null) {
                customerName = sale.getCustomer().getName();
            }

            // CALCULATIONS
            totalAmount = sale.getFinalAmount();

            paidAmount = paymentRepository.findBySaleId(sale.getId())
                    .stream()
                    .mapToDouble(Payment::getAmount)
                    .sum();

            remainingAmount = totalAmount - paidAmount;
        }

        return new PaymentResponseDTO(
                payment.getId(),
                payment.getPaymentDate(),
                vehicleName,
                customerName,
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getTransactionId(),
                saleId,
                totalAmount,
                paidAmount,
                remainingAmount
        );
    }
}