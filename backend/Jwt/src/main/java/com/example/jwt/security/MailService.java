package com.example.jwt.security;

import com.example.jwt.dtos.MailDTO;

public interface MailService {
    public void sendCodeByEmail(MailDTO mailDTO);
}
