package com.example.payment_service.dtos;


import lombok.Data;

@Data
public class PaymentInput {

    private int value;
    private String privateKey;
    private String from;
    private String immoId;

}
