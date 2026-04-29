package com.example.AutomobileApplication.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleName;
    private String brand;
    private String model;

    private Double price;
    private int stockQuantity;

    private String fuelType;       // PETROL / DIESEL / ELECTRIC
    private String transmission;   // MANUAL / AUTOMATIC

    private String color;
    private int manufactureYear;

    private String registrationNumber;

    private String status; // AVAILABLE / SOLD

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}