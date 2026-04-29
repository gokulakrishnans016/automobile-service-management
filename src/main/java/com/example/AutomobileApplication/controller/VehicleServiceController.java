package com.example.AutomobileApplication.controller;

import com.example.AutomobileApplication.dto.VehicleServiceRequestDTO;
import com.example.AutomobileApplication.dto.VehicleServiceResponseDTO;
import com.example.AutomobileApplication.entity.User;
import com.example.AutomobileApplication.service.UserService;
import com.example.AutomobileApplication.service.VehicleServiceService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle-services")
@CrossOrigin("*")
public class VehicleServiceController {

    @Autowired
    private VehicleServiceService vehicleServiceService;

    @Autowired
    private UserService userService;

    //  CREATE
    @PostMapping
    public VehicleServiceResponseDTO create(
            @RequestParam String username,
            @Valid @RequestBody VehicleServiceRequestDTO dto) {

        User user = userService.findByUsernameOrEmail(username);

        if (!userService.isAdmin(user) && !userService.isUser(user)) {
            throw new RuntimeException("Access denied");
        }

        return vehicleServiceService.createService(dto);
    }

    // GET ALL
    @GetMapping
    public List<VehicleServiceResponseDTO> getAll(
            @RequestParam String username) {

        User user = userService.findByUsernameOrEmail(username);

        if (!userService.isAdmin(user) && !userService.isUser(user)) {
            throw new RuntimeException("Access denied");
        }

        return vehicleServiceService.getAll();
    }

    //  UPDATE
    @PutMapping("/{id}/status")
    public VehicleServiceResponseDTO updateStatus(
            @PathVariable Long id,
            @RequestParam String username,
            @RequestParam String status) {

        User user = userService.findByUsernameOrEmail(username);

        if (!userService.isAdmin(user)) {
            throw new RuntimeException("Only ADMIN can update");
        }

        return vehicleServiceService.updateStatus(id, status);
    }

    @GetMapping("/count")
    public long getServiceCount(@RequestParam String username) {

        User user = userService.findByUsernameOrEmail(username);

        if (!userService.isAdmin(user) && !userService.isUser(user)) {
            throw new RuntimeException("Access denied");
        }

        return vehicleServiceService.getServiceCount();
    }

    //  DELETE
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestParam String username) {

        User user = userService.findByUsernameOrEmail(username);

        if (!userService.isAdmin(user)) {
            throw new RuntimeException("Only ADMIN can delete");
        }

        vehicleServiceService.deleteService(id);

        return "Deleted successfully";
    }

}