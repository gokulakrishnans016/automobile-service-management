package com.example.AutomobileApplication.mapper;

import com.example.AutomobileApplication.dto.VehicleServiceRequestDTO;
import com.example.AutomobileApplication.dto.VehicleServiceResponseDTO;
import com.example.AutomobileApplication.entity.Customer;
import com.example.AutomobileApplication.entity.Vehicle;
import com.example.AutomobileApplication.entity.VehicleService;

public class VehicleServiceMapper {

    // ================= ENTITY =================
    public static VehicleService toEntity(
            VehicleServiceRequestDTO dto,
            Vehicle vehicle,
            Customer customer) {

        VehicleService vs = new VehicleService();

        vs.setVehicle(vehicle);
        vs.setCustomer(customer);

        vs.setServiceType(String.join(",", dto.getServiceType()));

        vs.setServiceDescription(dto.getServiceDescription());
        vs.setServiceCost(dto.getServiceCost());
        vs.setNextServiceDate(dto.getNextServiceDate());

        return vs;
    }

    // ================= DTO =================
    public static VehicleServiceResponseDTO toDTO(VehicleService vs) {

        String vehicleName = null;
        String customerName = null;

        if (vs.getVehicle() != null) {
            vehicleName = vs.getVehicle().getVehicleName();
        }

        if (vs.getCustomer() != null) {
            customerName = vs.getCustomer().getName();
        }

        return new VehicleServiceResponseDTO(
                vs.getId(),
                vs.getServiceDate(),
                vs.getServiceType(),
                vs.getServiceDescription(),
                vs.getServiceCost(),
                vs.getStatus(),
                vs.getNextServiceDate(),
                vehicleName,
                customerName
        );
    }
}