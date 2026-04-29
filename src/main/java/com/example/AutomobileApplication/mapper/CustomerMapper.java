package com.example.AutomobileApplication.mapper;

import com.example.AutomobileApplication.dto.CustomerRequestDTO;
import com.example.AutomobileApplication.dto.CustomerResponseDTO;
import com.example.AutomobileApplication.entity.Customer;

import java.time.LocalDateTime;
public class CustomerMapper {

    // DTO TO ENTITY
    public static Customer toEntity(CustomerRequestDTO dto) {

        Customer customer = new Customer();

        customer.setName(dto.getName());
        customer.setAge(dto.getAge());
        customer.setDateOfBirth(dto.getDateOfBirth());
        customer.setGender(dto.getGender());

        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());

        customer.setAddress(dto.getAddress());
        customer.setCity(dto.getCity());
        customer.setState(dto.getState());
        customer.setPincode(dto.getPincode());

        customer.setDrivingLicenseNumber(dto.getDrivingLicenseNumber());

        customer.setCreatedAt(LocalDateTime.now());

        return customer;
    }

    // ENTITY TO DTO
    public static CustomerResponseDTO toDTO(Customer customer) {

        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getAge(),
                customer.getDateOfBirth(),
                customer.getGender(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getCity(),
                customer.getState(),
                customer.getPincode(),
                customer.getDrivingLicenseNumber(),
                customer.getCreatedAt()
        );
    }
}