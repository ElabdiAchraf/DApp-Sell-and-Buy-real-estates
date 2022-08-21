package com.example.payment_service.dtos;


import lombok.Data;

@Data
public class AcceptPaymentInput {
    private int value;
    private String to;
}
