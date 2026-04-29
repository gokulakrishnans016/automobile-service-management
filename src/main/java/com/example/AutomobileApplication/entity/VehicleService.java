package com.example.AutomobileApplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate serviceDate;

    private String serviceType;

    private String serviceDescription;

    private Double serviceCost;

    private String status;

    private LocalDate nextServiceDate;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}