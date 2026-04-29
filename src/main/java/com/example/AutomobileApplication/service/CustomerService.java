package com.example.AutomobileApplication.service;

import com.example.AutomobileApplication.dto.CustomerRequestDTO;
import com.example.AutomobileApplication.dto.CustomerResponseDTO;
import com.example.AutomobileApplication.entity.Customer;
import com.example.AutomobileApplication.exception.ResourceNotFoundException;
import com.example.AutomobileApplication.mapper.CustomerMapper;
import com.example.AutomobileApplication.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // CREATE
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {

        Customer customer = CustomerMapper.toEntity(dto);
        Customer saved = customerRepository.save(customer);

        return CustomerMapper.toDTO(saved);
    }

    // GET ALL
    public List<CustomerResponseDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toDTO)
                .toList();
    }

    // GET BY ID
    public CustomerResponseDTO getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return CustomerMapper.toDTO(customer);
    }

    // UPDATE
    public CustomerResponseDTO updateCustomer(Long id, CustomerRequestDTO dto) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

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

        Customer updated = customerRepository.save(customer);

        return CustomerMapper.toDTO(updated);
    }

    // DELETE
    public void deleteCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        customerRepository.delete(customer);
    }
}