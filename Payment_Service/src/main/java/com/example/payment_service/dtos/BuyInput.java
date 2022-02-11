package com.example.payment_service.dtos;


import lombok.Data;

@Data
public class BuyInput {

    private String _newOwner;
    private PaymentInput paymentInput;

}
