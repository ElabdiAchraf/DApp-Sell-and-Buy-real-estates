package com.example.payment_service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChangeOwnershipInput2 {
    private String privateKey;
    private String _propId;
    private String _newOwner;
    private String username;


}
