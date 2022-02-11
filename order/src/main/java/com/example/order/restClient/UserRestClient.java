package com.example.order.restClient;


import com.example.order.Dtos.UserExistDto;
import com.example.order.Dtos.UserResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "AUTH-SERVICE")
public interface UserRestClient {
    @RequestMapping(method = RequestMethod.POST, value = "/getUser", consumes = "application/json")
    UserResp getUser(UserExistDto userExistDto);
//    @RequestMapping(method = RequestMethod.POST, value = "/approveUser", consumes = "application/json")
//    String approveUserToContarct(UserInput userInput);
}
