package com.example.payment_service.dtos;


import com.example.payment_service.dtos.StatusPayment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ForSellInfo {
    @Id
    private  String id ;
    private  String immoid ;
    private  String privateKey;
    private  String userneme;
}
