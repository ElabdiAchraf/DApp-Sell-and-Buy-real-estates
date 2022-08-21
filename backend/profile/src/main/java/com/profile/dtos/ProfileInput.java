package com.profile.dtos;

import lombok.Data;

@Data
public class ProfileInput {
    private  String firstName;
    private String LastName;
    private String address;
    private String photo;
    private String phone;
    private String username;
}
