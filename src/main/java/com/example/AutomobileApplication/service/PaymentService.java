package com.example.AutomobileApplication.service;

import com.example.AutomobileApplication.dto.PaymentItemDTO;
import com.example.AutomobileApplication.dto.PaymentRequestDTO;
import com.example.AutomobileApplication.dto.PaymentResponseDTO;
import com.example.AutomobileApplication.dto.PaymentSummaryDTO;
import com.example.AutomobileApplication.entity.Payment;
import com.example.AutomobileApplication.entity.Sale;
import com.example.AutomobileApplication.exception.ResourceNotFoundException;
import com.example.AutomobileApplication.mapper.PaymentMapper;
import com.example.AutomobileApplication.repository.PaymentRepository;
import com.example.AutomobileApplication.repository.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private SaleRepository saleRepository;

    // CREATE PAYMENT
    public PaymentResponseDTO createPayment(PaymentRequestDTO dto) {

        Sale sale = saleRepository.findById(dto.getSaleId())
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));

        Payment payment = new Payment();

        double totalPaid = paymentRepository.findBySaleId(sale.getId())
                .stream()
                .mapToDouble(Payment::getAmount)
                .sum();

        double newTotal = totalPaid + dto.getAmount();
        double remaining = sale.getFinalAmount() - newTotal;

        if (remaining <= 0) {
            payment.setPaymentStatus("SUCCESS");
        } else {
            payment.setPaymentStatus("PENDING");
        }


        payment.setSale(sale);
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setTransactionId(dto.getTransactionId());
        payment.setPaymentDate(LocalDateTime.now());

        Payment saved = paymentRepository.save(payment);

        return PaymentMapper.toDTO(saved, paymentRepository);
    }

    // GET ALL
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(p -> PaymentMapper.toDTO(p, paymentRepository))
                .toList();
    }

    // GET BY ID
    public PaymentResponseDTO getPaymentById(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        return PaymentMapper.toDTO(payment, paymentRepository);
    }

    // GET BY SALE
    public List<PaymentResponseDTO> getPaymentsBySale(Long saleId) {

        return paymentRepository.findBySaleId(saleId)
                .stream()
                .map(p -> PaymentMapper.toDTO(p, paymentRepository))
                .toList();
    }

    // DELETE
    public void deletePayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found"));

        paymentRepository.delete(payment);
    }

    public List<PaymentSummaryDTO> getPaymentSummary() {

        List<Payment> allPayments = paymentRepository.findAll();

        Map<Long, List<Payment>> grouped = allPayments.stream()
                .collect(Collectors.groupingBy(p -> p.getSale().getId()));

        List<PaymentSummaryDTO> result = new ArrayList<>();

        for (Long saleId : grouped.keySet()) {

            List<Payment> payments = grouped.get(saleId);
            Sale sale = payments.get(0).getSale();

            double total = sale.getFinalAmount();
            double paid = payments.stream().mapToDouble(Payment::getAmount).sum();
            double remaining = total - paid;

            List<PaymentItemDTO> items = payments.stream().map(p ->
                    new PaymentItemDTO(
                            p.getAmount(),
                            p.getPaymentMethod(),
                            p.getPaymentStatus(),
                            p.getTransactionId(),
                            p.getPaymentDate()
                    )
            ).toList();

            PaymentSummaryDTO dto = new PaymentSummaryDTO(
                    saleId,
                    sale.getCustomer().getId(),
                    sale.getVehicle().getVehicleName(),
                    sale.getCustomer().getName(),
                    total,
                    paid,
                    remaining,
                    payments.size(),
                    payments.stream()
                            .map(Payment::getPaymentDate)
                            .max(LocalDateTime::compareTo)
                            .orElse(null),
                    items
            );

            result.add(dto);
        }

        return result;
    }
}