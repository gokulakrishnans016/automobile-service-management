package com.example.AutomobileApplication.mapper;

import com.example.AutomobileApplication.dto.SaleResponseDTO;
import com.example.AutomobileApplication.entity.Sale;

public class SaleMapper {

    public static SaleResponseDTO toDTO(Sale sale) {

        SaleResponseDTO dto = new SaleResponseDTO();

        dto.setId(sale.getId());
        dto.setSaleDate(sale.getSaleDate());
        dto.setVehicleName(sale.getVehicle().getVehicleName());
        dto.setCustomerName(sale.getCustomer().getName());
        dto.setSalePrice(sale.getSalePrice());
        dto.setDiscount(sale.getDiscount());
        dto.setTaxAmount(sale.getTaxAmount());
        dto.setFinalAmount(sale.getFinalAmount());
        dto.setStatus(sale.getStatus());
        dto.setDeliveryDate(sale.getDeliveryDate());

        return dto;
    }
}