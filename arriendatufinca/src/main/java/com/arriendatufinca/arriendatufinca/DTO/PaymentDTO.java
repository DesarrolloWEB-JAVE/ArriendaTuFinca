package com.arriendatufinca.arriendatufinca.DTO;

import java.time.LocalDateTime;

import com.arriendatufinca.arriendatufinca.Enums.PaymentState;
import com.arriendatufinca.arriendatufinca.Enums.StatusEnum;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PaymentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private double amount;
    private PaymentState state;
    private LocalDateTime paymentDate;
    private Integer cardNumber;
    private String cardHolderName;
    private String cardExpirationDate;
    private Integer cardSecurityCode;
    private StatusEnum status;
}
