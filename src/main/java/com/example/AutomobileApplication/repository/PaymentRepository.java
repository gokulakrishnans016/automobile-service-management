package com.example.AutomobileApplication.repository;

import com.example.AutomobileApplication.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findBySaleId(Long saleId);
}