package com.example.AutomobileApplication.repository;

import com.example.AutomobileApplication.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

}