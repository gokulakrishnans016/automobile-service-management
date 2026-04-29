package com.example.AutomobileApplication.service;

import com.example.AutomobileApplication.dto.SaleRequestDTO;
import com.example.AutomobileApplication.dto.SaleResponseDTO;
import com.example.AutomobileApplication.entity.Customer;
import com.example.AutomobileApplication.entity.Sale;
import com.example.AutomobileApplication.entity.Vehicle;
import com.example.AutomobileApplication.exception.ResourceNotFoundException;
import com.example.AutomobileApplication.mapper.SaleMapper;
import com.example.AutomobileApplication.repository.CustomerRepository;
import com.example.AutomobileApplication.repository.SaleRepository;
import com.example.AutomobileApplication.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // CREATE SALE
    @Transactional
    public SaleResponseDTO createSale(SaleRequestDTO dto) {

        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        //  CHECK STOCK
        if (vehicle.getStockQuantity() <= 0) {
            throw new RuntimeException("Vehicle out of stock");
        }

        //  CHECK STATUS
        if (!vehicle.getStatus().equalsIgnoreCase("AVAILABLE")) {
            throw new RuntimeException("Vehicle is not available for sale");
        }

        Vehicle soldVehicle = new Vehicle();

        soldVehicle.setBrand(vehicle.getBrand());
        soldVehicle.setModel(vehicle.getModel());
        soldVehicle.setVehicleName(vehicle.getVehicleName());
        soldVehicle.setPrice(vehicle.getPrice());
        soldVehicle.setFuelType(vehicle.getFuelType());
        soldVehicle.setManufactureYear(vehicle.getManufactureYear());

        // assign customer
        soldVehicle.setCustomer(customer);

        // mark sold
        soldVehicle.setStatus("SOLD");
        soldVehicle.setStockQuantity(0);

        // save sold vehicle
        vehicleRepository.save(soldVehicle);

        vehicle.setStockQuantity(vehicle.getStockQuantity() - 1);

        if (vehicle.getStockQuantity() == 0) {
            vehicle.setStatus("SOLD");
        }

        vehicleRepository.save(vehicle);

        Sale sale = new Sale();
        sale.setVehicle(soldVehicle);
        sale.setCustomer(customer);
        sale.setSalePrice(dto.getSalePrice());
        sale.setDiscount(dto.getDiscount());
        sale.setTaxAmount(dto.getTaxAmount());
        sale.setStatus(dto.getStatus());
        sale.setDeliveryDate(dto.getDeliveryDate());
        sale.setSaleDate(LocalDateTime.now());

        double discount = dto.getDiscount() != null ? dto.getDiscount() : 0;
        double tax = dto.getTaxAmount() != null ? dto.getTaxAmount() : 0;

        double finalAmount = dto.getSalePrice() - discount + tax;
        sale.setFinalAmount(finalAmount);

        saleRepository.save(sale);

        return SaleMapper.toDTO(sale);
    }

    // GET ALL
    public List<SaleResponseDTO> getAllSales() {
        return saleRepository.findAll()
                .stream()
                .map(SaleMapper::toDTO)
                .toList();
    }

    // GET BY ID
    public SaleResponseDTO getById(Long id) {

        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));

        return SaleMapper.toDTO(sale);
    }

    // DELETE
    @Transactional
    public void delete(Long id) {

        Sale sale = saleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sale not found"));

        Vehicle vehicle = sale.getVehicle();
        vehicleRepository.delete(vehicle);
        saleRepository.delete(sale);
    }

    // CANCEL SALE
    @Transactional
    public void cancelSale(Long id) {

        Sale sale = saleRepository.findById(id).orElseThrow(() -> new RuntimeException("Sale not found"));

        Vehicle vehicle = sale.getVehicle();
        vehicleRepository.delete(vehicle);
        sale.setStatus("CANCELLED");

        saleRepository.save(sale);
    }
}