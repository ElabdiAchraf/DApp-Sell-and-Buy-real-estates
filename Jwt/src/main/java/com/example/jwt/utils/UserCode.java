package com.example.jwt.utils;

import java.util.UUID;

public class UserCode {
    public static String getCode(){
        return UUID.randomUUID().toString();
    }
}
