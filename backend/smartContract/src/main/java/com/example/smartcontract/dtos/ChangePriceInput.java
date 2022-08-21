package com.example.smartcontract.dtos;


import lombok.Data;

@Data
public class ChangePriceInput {
    private int newprice;
    private String immo_id;
}
