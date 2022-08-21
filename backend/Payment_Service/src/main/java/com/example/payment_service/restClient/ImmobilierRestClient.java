package com.example.payment_service.restClient;



import com.example.payment_service.dtos.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "CONTRACT-SERVICE")
public interface ImmobilierRestClient {
    @RequestMapping(method = RequestMethod.POST, value = "/sellImmobilier", consumes = "application/json")
    Immobillier forSell(SellInput sellInput);


//    @RequestMapping(method = RequestMethod.POST, value = "/approveUser", consumes = "application/json")
//    String approveUserToContarct(UserInput userInput);



    @RequestMapping(method = RequestMethod.POST, value = "/changeOwnerShip2", consumes = "application/json")
    String changeOwnerShip(ChangeOwnershipInput2 changeOwnershipInput);


    @RequestMapping(method = RequestMethod.POST, value = "/approveChangeOwnerShip", consumes = "application/json")
    Immobillier approveChangeOwnerShip(ApproveImmoInput approveImmoInput);


    @RequestMapping(method = RequestMethod.GET, value = "/immobillierDetails/{id}")
    Immobillier immobillierDetails(@PathVariable("id") String id);

}
