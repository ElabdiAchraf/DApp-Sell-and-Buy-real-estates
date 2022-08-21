package com.example.smartcontract.controller;


import com.example.smartcontract.dtos.*;
import com.example.smartcontract.entities.Immobillier;
import com.example.smartcontract.services.ImmobillierContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ImmobillierController {

    @Autowired
    ImmobillierContractService immobillierContractService;

    @PostMapping("/addUser")

    public String addUser(@RequestBody() UserInput userInput) throws Exception {

       return immobillierContractService.addNewUser(userInput.getUser());
    }
    @PostMapping("/sellImmobilier")
    public Immobillier sellImmobilier(@RequestBody() SellInput sellInput) throws Exception {
        return immobillierContractService.forSell(sellInput.getImmo_id());
    }
//    @PostMapping("/changePrice")
//    public Immobillier changePrice(@RequestBody() SellInput sellInput) throws Exception {
//        return immobillierContractService.forSell(sellInput);
//    }
    @PostMapping("/approveUser")
    public String  approveUser(@RequestBody() UserInput userInput) throws Exception {
        return immobillierContractService.approveUsers(userInput.getUser());
    }
    @PostMapping("/addImmobillier")
    public Immobillier addImmobillier(@RequestBody() ImmobilierInput immobilierInput) throws Exception {
       return immobillierContractService.createProperty(immobilierInput);
    }

    @PostMapping("/approveImmobillier")
    public StatusImmobilier approveImmobillier(@RequestBody() ApproveImmoInput approveImmoInput) throws Exception {
       return immobillierContractService.approveProperty(approveImmoInput.getPropId());
    }

    @PostMapping("/changeOwnerShip")
    public String  changeOwnerShip( @RequestBody() ChangeOwnershipInput changeOwnershipInput) throws Exception {
       return immobillierContractService.changeOwnership(changeOwnershipInput);
    }
    @PostMapping("/changeOwnerShip2")
    public String  changeOwnerShip2( @RequestBody() ChangeOwnershipInput2 changeOwnershipInput) throws Exception {
        return immobillierContractService.changeOwnership2(changeOwnershipInput);
    }

    @PostMapping("/approveChangeOwnerShip")
    public Immobillier approveChangeOwnerShip(  @RequestBody() ApproveImmoInput approveImmoInput) throws Exception {
        return immobillierContractService.approveChangeOwnership(approveImmoInput.getPropId());
    }

    @GetMapping("/immobillierDetails/{id}")
    public Immobillier immobillierDetails( @PathVariable String id) throws Exception {
        System.out.println("BigInteger id : " + id);
        return   immobillierContractService.getPropertyDetails(id);
    }

    @GetMapping("/immobiliers")
    public List<Immobillier> immobilliers() throws Exception {
        return   immobillierContractService.allImmobiliers();
    }
    @GetMapping("/myImmobiliers")
    public List<Immobillier> myImmobiliers() throws Exception {
        return   immobillierContractService.myImmobiliers();
    }
    @GetMapping("/sell_immobiliers")
    public List<Immobillier> forSellImmobilliers() throws Exception {
        return   immobillierContractService.forSellImmobiliers();
    }
    @GetMapping("/approvedImmobiliers")
    public List<Immobillier> approvedImmobiliers() throws Exception {
        return   immobillierContractService.approvedImmobiliers();
    }
    @GetMapping("/waitingImmobiliers")
    public List<Immobillier> waitingImmobiliers() throws Exception {
        return   immobillierContractService.waitingImmobiliers();
    }
    @GetMapping("/waitingChangeImmobiliers")
    public List<Immobillier> waitingChangeImmobiliers() throws Exception {
        return   immobillierContractService.waitingChangeImmobiliers();
    }
}
