package com.example.smartcontract.dtos;

import lombok.Data;

@Data
public class ChangeOwnershipInput {
    private String privateKey;
    private String _propId;
    private String _newOwner;
}
