package com.example.smartcontract.restClient;


import com.example.smartcontract.dtos.UserByAddressDto;
import com.example.smartcontract.dtos.UserExistDto;
import com.example.smartcontract.dtos.UserResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AUTH-SERVICE")
public interface UserRestClient {
    @RequestMapping(method = RequestMethod.POST, value = "/getUser", consumes = "application/json")
    UserResp getUser(UserExistDto userExistDto);
//    @RequestMapping(method = RequestMethod.POST, value = "/approveUser", consumes = "application/json")
//    String approveUserToContarct(UserInput userInput);
    @RequestMapping(method = RequestMethod.POST, value = "/getUserByAddress", consumes = "application/json")
    UserResp getUserByAddress(UserByAddressDto userByAddressDto);
}
