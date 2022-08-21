package com.example.payment_service.dtos;


import lombok.Data;

@Data
public class ForSellInput {
    private int value;
    private String privateKey;
    private String immoId;

}
