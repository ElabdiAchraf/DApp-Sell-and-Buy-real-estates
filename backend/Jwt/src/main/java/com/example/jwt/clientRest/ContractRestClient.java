package com.example.jwt.clientRest;


import com.example.jwt.dtos.UserInput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "CONTRACT-SERVICE")
public interface ContractRestClient {
    @RequestMapping(method = RequestMethod.POST, value = "/addUser", consumes = "application/json")
    String addUserToContarct(UserInput userInput);
    @RequestMapping(method = RequestMethod.POST, value = "/approveUser", consumes = "application/json")
    String approveUserToContarct(UserInput userInput);
}
