package com.example.AutomobileApplication.repository;

import com.example.AutomobileApplication.entity.VehicleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleServiceRepository extends JpaRepository<VehicleService, Long> {

    List<VehicleService> findByCustomerId(Long customerId);

}