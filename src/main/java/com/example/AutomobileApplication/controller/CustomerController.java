package com.example.AutomobileApplication.controller;

import com.example.AutomobileApplication.dto.CustomerRequestDTO;
import com.example.AutomobileApplication.dto.CustomerResponseDTO;
import com.example.AutomobileApplication.service.CustomerService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // CREATE
    @PostMapping
    public CustomerResponseDTO create(@Valid @RequestBody CustomerRequestDTO dto) {
        return customerService.createCustomer(dto);
    }

    // GET ALL
    @GetMapping
    public List<CustomerResponseDTO> getAll() {
        return customerService.getAllCustomers();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CustomerResponseDTO getById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public CustomerResponseDTO update(@PathVariable Long id,
                                      @Valid @RequestBody CustomerRequestDTO dto) {
        return customerService.updateCustomer(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return "Customer deleted successfully";
    }
}