package com.example.payment_service.controller;

import com.example.payment_service.dtos.*;
import com.example.payment_service.dtos.ForSellInfo;
import com.example.payment_service.services.PaymentContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
public class PaymentController {
    @Autowired
    PaymentContractService paymentContractService;

    @PostMapping("/pay")
    public BigInteger pay(@RequestBody() PaymentInput paymentInput) throws Exception {
        return paymentContractService.invest(paymentInput);
    }

    @PostMapping("/payTaxe")
    public BigInteger pay(@RequestBody() TaxeInput taxeInput) throws Exception {
        return paymentContractService.taxe(taxeInput);
    }

    @PostMapping("/acceptPayment")
    public BigInteger acceptPayment(@RequestBody() AcceptPaymentInput acceptPaymentInput) throws Exception {
        return paymentContractService.acceptePayment(acceptPaymentInput.getTo(), acceptPaymentInput.getValue());
    }

    @PostMapping("/acceptChange")
    public Immobillier acceptChange(@RequestBody() ApproveImmoInput approveImmoInput) throws Exception {
        return paymentContractService.accepteChange( approveImmoInput);
    }

    @PostMapping("/refuseChange")
    public String refuseChange(@RequestBody()  ApproveImmoInput approveImmoInput) throws Exception {
        return paymentContractService.refuseChange(approveImmoInput);
    }
    @PostMapping("/sell")
    public ForSellInfo sell(@RequestBody() ForSellInput forSellInput) throws Exception {
        return paymentContractService.forSell(forSellInput);
    }

    @PostMapping("/buy")
    public String buy(@RequestBody() BuyInput buyInput) throws Exception {
        return paymentContractService.buy(buyInput);
    }

    @GetMapping("/balance")
    public BigInteger balance() throws Exception {
        System.out.println("----------------------------------------------------");
        return paymentContractService.balanceOf();
    }

}
