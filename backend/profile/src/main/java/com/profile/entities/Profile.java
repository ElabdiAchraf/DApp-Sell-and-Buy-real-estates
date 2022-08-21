package com.profile.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profile {
    @Id
    private BigInteger id;
    private  String firstName;
    private String LastName;
    private String address;
    private String photo;
    private String phone;
    private Long userId;
}
