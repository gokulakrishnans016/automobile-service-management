package com.example.AutomobileApplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private String password;

    private LocalDate dateOfBirth;

    private String gender;

    private String role; // ADMIN / USER

    private boolean active = true;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}