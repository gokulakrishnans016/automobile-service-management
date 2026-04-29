package com.example.AutomobileApplication.controller;

import com.example.AutomobileApplication.dto.SaleRequestDTO;
import com.example.AutomobileApplication.dto.SaleResponseDTO;
import com.example.AutomobileApplication.service.SaleService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/sales")
@CrossOrigin("*")
public class SaleController {

    @Autowired
    private SaleService saleService;

    // CREATE
    @PostMapping
    public SaleResponseDTO create(@Valid @RequestBody SaleRequestDTO dto) {
        return saleService.createSale(dto);
    }

    // GET ALL
    @GetMapping
    public List<SaleResponseDTO> getAll() {
        return saleService.getAllSales();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public SaleResponseDTO getById(@PathVariable Long id) {
        return saleService.getById(id);
    }

    // CANCEL
    @PutMapping("/cancel/{id}")
    public String cancel(@PathVariable Long id) {
        saleService.cancelSale(id);
        return "Sale cancelled successfully";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        saleService.delete(id);
        return "Sale deleted successfully";
    }

}