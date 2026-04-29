/*
package com.example.AutomobileApplication.mapper;

import com.example.AutomobileApplication.dto.VehicleRequestDTO;
import com.example.AutomobileApplication.dto.VehicleResponseDTO;
import com.example.AutomobileApplication.entity.Vehicle;

public class VehicleMapper {

    // DTO → ENTITY
    public static Vehicle toEntity(VehicleRequestDTO dto) {

        Vehicle v = new Vehicle();

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
        v.setStatus(dto.getStatus());

        return v;
    }

    // ENTITY → DTO
    public static VehicleResponseDTO toDTO(Vehicle v) {

        String customerName = null;
        Long customerId = null;

        if (v.getCustomer() != null) {
            customerName = v.getCustomer().getName();
            customerId = v.getCustomer().getId();
        }

        VehicleResponseDTO dto = new VehicleResponseDTO();

        dto.setId(v.getId());
        dto.setVehicleName(v.getVehicleName());
        dto.setBrand(v.getBrand());
        dto.setModel(v.getModel());
        dto.setPrice(v.getPrice());
        dto.setStockQuantity(v.getStockQuantity());
        dto.setFuelType(v.getFuelType());
        dto.setTransmission(v.getTransmission());
        dto.setColor(v.getColor());
        dto.setManufactureYear(v.getManufactureYear());
        dto.setRegistrationNumber(v.getRegistrationNumber());
        dto.setStatus(v.getStatus());

        dto.setCustomerName(customerName); // ✅ IMPORTANT
        dto.setCustomerId(customerId);     // ✅ IMPORTANT

        return dto;
    }}*/


package com.example.AutomobileApplication.mapper;

import com.example.AutomobileApplication.dto.VehicleRequestDTO;
import com.example.AutomobileApplication.dto.VehicleResponseDTO;
import com.example.AutomobileApplication.entity.Vehicle;

public class VehicleMapper {

    // DTO TO ENTITY
    public static Vehicle toEntity(VehicleRequestDTO dto) {

        if (dto == null) return null;

        Vehicle v = new Vehicle();

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
        v.setStatus(dto.getStatus());

        return v;
    }

    // ENTITY TO DTO
    public static VehicleResponseDTO toDTO(Vehicle v) {

        if (v == null) return null;

        VehicleResponseDTO dto = new VehicleResponseDTO();

        dto.setId(v.getId());
        dto.setVehicleName(v.getVehicleName());
        dto.setBrand(v.getBrand());
        dto.setModel(v.getModel());
        dto.setPrice(v.getPrice());
        dto.setStockQuantity(v.getStockQuantity());
        dto.setFuelType(v.getFuelType());
        dto.setTransmission(v.getTransmission());
        dto.setColor(v.getColor());
        dto.setManufactureYear(v.getManufactureYear());
        dto.setRegistrationNumber(v.getRegistrationNumber());
        dto.setStatus(v.getStatus());

        if (v.getCustomer() != null)
        {
            dto.setCustomerName(v.getCustomer().getName());
            dto.setCustomerId(v.getCustomer().getId());
        } else
        {
            dto.setCustomerName("Not Assigned");
            dto.setCustomerId(null);
        }

        return dto;
    }
}