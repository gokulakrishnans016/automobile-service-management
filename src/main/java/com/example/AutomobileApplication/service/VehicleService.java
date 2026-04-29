package com.example.AutomobileApplication.service;

import com.example.AutomobileApplication.dto.VehicleRequestDTO;
import com.example.AutomobileApplication.dto.VehicleResponseDTO;
import com.example.AutomobileApplication.entity.Vehicle;
import com.example.AutomobileApplication.exception.ResourceNotFoundException;
import com.example.AutomobileApplication.mapper.VehicleMapper;
import com.example.AutomobileApplication.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    // CREATE
    public VehicleResponseDTO create(VehicleRequestDTO dto) {

        Vehicle v = VehicleMapper.toEntity(dto);

        if (v.getStatus() == null || v.getStatus().isEmpty()) {
            v.setStatus("AVAILABLE");
        }

        Vehicle saved = vehicleRepository.save(v);

        return VehicleMapper.toDTO(saved);
    }

    // GET ALL
    public List<VehicleResponseDTO> getAll() {

        return vehicleRepository.findAll()
                .stream()
                .map(VehicleMapper::toDTO)
                .toList();
    }

    // GET BY ID
    public VehicleResponseDTO getById(Long id) {

        Vehicle v = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        return VehicleMapper.toDTO(v);
    }

    // UPDATE
    public VehicleResponseDTO update(Long id, VehicleRequestDTO dto) {

        Vehicle v = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        v.setVehicleName(dto.getVehicleName());
        v.setBrand(dto.getBrand());
        v.setModel(dto.getModel());
        v.setPrice(dto.getPrice());
        v.setStockQuantity(dto.getStockQuantity());
        v.setFuelType(dto.getFuelType());
        v.setTransmission(dto.getTransmission());
        v.setColor(dto.getColor());
        v.setManufactureYear(dto.getManufactureYear());
        v.setRegistrationNumber(dto.getRegistrationNumber());

        if (dto.getStatus() != null) {
            v.setStatus(dto.getStatus());
        }

        Vehicle updated = vehicleRepository.save(v);

        return VehicleMapper.toDTO(updated);
    }

    // DELETE
    public void delete(Long id) {

        Vehicle v = vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));

        vehicleRepository.delete(v);
    }
}