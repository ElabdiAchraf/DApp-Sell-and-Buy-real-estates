package com.example.payment_service.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class Immobillier {

    private String id ;
    private int price;
    private  String localisation;
    private  String category;
    private  String ownerName;
    private String ownerAddress;
    private boolean forSell;
    private  String description;
    private Date creation_date;
    private StatusImmobilier status;
}
