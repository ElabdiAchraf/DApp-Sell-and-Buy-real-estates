package com.example.jwt.dtos;

import lombok.Data;

@Data
 public class UserForm{
    private String username;
    private String password;
    private String confirmedPassword;
    private String address;
}
