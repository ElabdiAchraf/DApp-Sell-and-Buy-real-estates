package com.example.order.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    @Id
    private String id;
    private String idImmobilier;
    private String BuyerAddress;
    private Long idUser;
    private int price;
}
