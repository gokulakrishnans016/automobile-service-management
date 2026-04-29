

package com.example.AutomobileApplication.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private LocalDate dateOfBirth;
    private String gender;

    private String email;
    private String phone;

    private String address;
    private String city;
    private String state;
    private String pincode;

    private String drivingLicenseNumber;

    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Sale> sales;
}