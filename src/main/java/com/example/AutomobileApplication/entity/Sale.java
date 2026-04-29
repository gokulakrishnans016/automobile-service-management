package com.example.AutomobileApplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Sale {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDateTime saleDate;

        private Double salePrice;
        private Double discount;
        private Double taxAmount;
        private Double finalAmount;

        private String status; // PENDING, COMPLETED, CANCELLED

        private LocalDate deliveryDate;

        @ManyToOne
        @JoinColumn(name = "vehicle_id")
        private Vehicle vehicle;

        @ManyToOne
        @JoinColumn(name = "customer_id")
        private Customer customer;
    }