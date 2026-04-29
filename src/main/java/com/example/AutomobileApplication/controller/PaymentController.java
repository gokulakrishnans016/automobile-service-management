package com.example.AutomobileApplication.controller;

import com.example.AutomobileApplication.dto.PaymentRequestDTO;
import com.example.AutomobileApplication.dto.PaymentResponseDTO;
import com.example.AutomobileApplication.dto.PaymentSummaryDTO;
import com.example.AutomobileApplication.entity.User;
import com.example.AutomobileApplication.service.PaymentService;
import com.example.AutomobileApplication.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private UserService userService;

    // CREATE (ADMIN ONLY)
    @PostMapping
    public PaymentResponseDTO createPayment(
            @RequestParam String username,
            @Valid @RequestBody PaymentRequestDTO dto) {

        User user = userService.findByUsernameOrEmail(username);

        if (!userService.isAdmin(user)) {
            throw new RuntimeException("Admin only");
        }

        return paymentService.createPayment(dto);
    }

    // GET ALL
    @GetMapping
    public List<PaymentResponseDTO> getAll(
            @RequestParam String username) {

        User user = userService.findByUsernameOrEmail(username);

        if (!userService.isAdmin(user) && !userService.isUser(user)) {
            throw new RuntimeException("Access denied");
        }

        return paymentService.getAllPayments();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public PaymentResponseDTO getById(
            @PathVariable Long id,
            @RequestParam String username) {

        User user = userService.findByUsernameOrEmail(username);

        if (!userService.isAdmin(user) && !userService.isUser(user)) {
            throw new RuntimeException("Access denied");
        }

        return paymentService.getPaymentById(id);
    }

    // GET BY SALE
    @GetMapping("/sale/{saleId}")
    public List<PaymentResponseDTO> getBySale(
            @PathVariable Long saleId,
            @RequestParam String username) {

        User user = userService.findByUsernameOrEmail(username);

        if (!userService.isAdmin(user) && !userService.isUser(user)) {
            throw new RuntimeException("Access denied");
        }

        return paymentService.getPaymentsBySale(saleId);
    }

    // DELETE (ADMIN ONLY)
    @DeleteMapping("/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestParam String username) {

        User user = userService.findByUsernameOrEmail(username);

        if (!userService.isAdmin(user)) {
            throw new RuntimeException("Admin only");
        }

        paymentService.deletePayment(id);

        return "Payment deleted successfully";
    }

    @GetMapping("/summary")
    public List<PaymentSummaryDTO> getSummary(@RequestParam String username) {

        User user = userService.findByUsernameOrEmail(username);

        if (!userService.isAdmin(user) && !userService.isUser(user)) {
            throw new RuntimeException("Access denied");
        }

        return paymentService.getPaymentSummary();
    }
}