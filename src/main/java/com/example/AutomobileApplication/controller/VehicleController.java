package com.example.AutomobileApplication.controller;

import com.example.AutomobileApplication.dto.VehicleRequestDTO;
import com.example.AutomobileApplication.dto.VehicleResponseDTO;
import com.example.AutomobileApplication.service.VehicleService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
@CrossOrigin("*")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // CREATE
    @PostMapping
    public VehicleResponseDTO create(@Valid @RequestBody VehicleRequestDTO dto) {
        return vehicleService.create(dto);
    }

    // GET ALL
    @GetMapping
    public List<VehicleResponseDTO> getAll() {
        return vehicleService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public VehicleResponseDTO getById(@PathVariable Long id) {
        return vehicleService.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public VehicleResponseDTO update(@PathVariable Long id,
                                     @Valid @RequestBody VehicleRequestDTO dto) {
        return vehicleService.update(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return "Vehicle deleted successfully";
    }
}