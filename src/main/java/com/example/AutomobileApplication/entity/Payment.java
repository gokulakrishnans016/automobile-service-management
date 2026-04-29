/*
package com.example.AutomobileApplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime paymentDate;

    private Double amount;

    private String paymentMethod; // CASH / CARD / UPI

    private String paymentStatus; // SUCCESS / FAILED / PENDING

    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @ManyToOne
    private Vehicle vehicle;

    @ManyToOne
    private Customer customer;
}*/


package com.example.AutomobileApplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime paymentDate;

    private Double amount;

    private String paymentMethod; // CASH / CARD / UPI

    private String paymentStatus; // SUCCESS / PENDING

    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;
}