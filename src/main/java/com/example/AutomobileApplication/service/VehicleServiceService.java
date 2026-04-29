package com.example.AutomobileApplication.service;

import com.example.AutomobileApplication.dto.VehicleServiceRequestDTO;
import com.example.AutomobileApplication.dto.VehicleServiceResponseDTO;
import com.example.AutomobileApplication.entity.Customer;
import com.example.AutomobileApplication.entity.Vehicle;
import com.example.AutomobileApplication.entity.VehicleService;
import com.example.AutomobileApplication.exception.ResourceNotFoundException;
import com.example.AutomobileApplication.mapper.VehicleServiceMapper;
import com.example.AutomobileApplication.repository.CustomerRepository;
import com.example.AutomobileApplication.repository.VehicleRepository;
import com.example.AutomobileApplication.repository.VehicleServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VehicleServiceService {

    @Autowired
    private VehicleServiceRepository repository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private CustomerRepository customerRepository;

    // CREATE SERVICE
    public VehicleServiceResponseDTO createService(VehicleServiceRequestDTO dto) {

        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        Customer customer = vehicle.getCustomer();

        if (customer == null) {
            throw new RuntimeException("Assign customer via SALE first");
        }

        VehicleService vs = VehicleServiceMapper.toEntity(dto, vehicle, customer);

        vs.setServiceDate(LocalDate.now());

        if (dto.getStatus() == null || dto.getStatus().isEmpty()) {
            vs.setStatus("PENDING");
        } else {
            vs.setStatus(dto.getStatus());
        }

        VehicleService saved = repository.save(vs);

        return VehicleServiceMapper.toDTO(saved);
    }
    // GET BY CUSTOMER
    public List<VehicleServiceResponseDTO> getByCustomer(Long customerId) {

        return repository.findByCustomerId(customerId)
                .stream()
                .map(VehicleServiceMapper::toDTO)
                .toList();
    }

    // GET ALL
    public List<VehicleServiceResponseDTO> getAll() {

        return repository.findAll()
                .stream()
                .map(VehicleServiceMapper::toDTO)
                .toList();
    }

    // GET BY ID
    public VehicleServiceResponseDTO getById(Long id) {

        VehicleService vs = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        return VehicleServiceMapper.toDTO(vs);
    }

    // UPDATE STATUS
    public VehicleServiceResponseDTO updateStatus(Long id, String status) {

        VehicleService vs = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        if ("COMPLETED".equalsIgnoreCase(vs.getStatus())) {
            throw new RuntimeException("Completed service cannot be modified");
        }

        vs.setStatus(status);

        VehicleService updated = repository.save(vs);

        return VehicleServiceMapper.toDTO(updated);
    }

    // COUNT
    public long getServiceCount() {
        return repository.count();
    }

    // DELETE
    public void deleteService(Long id) {

        VehicleService vs = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        repository.delete(vs);
    }
}