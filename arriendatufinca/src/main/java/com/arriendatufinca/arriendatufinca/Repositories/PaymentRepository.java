package com.arriendatufinca.arriendatufinca.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arriendatufinca.arriendatufinca.Entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}