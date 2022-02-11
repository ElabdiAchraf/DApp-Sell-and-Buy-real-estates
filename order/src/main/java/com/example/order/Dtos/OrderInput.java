package com.example.order.Dtos;

import lombok.Data;

@Data
public class OrderInput {
    private String BuyerAddress;
    private int price;
    private String idImmobilier;
    private Long idUser;
}
