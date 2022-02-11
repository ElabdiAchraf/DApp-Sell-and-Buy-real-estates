package com.example.payment_service.services;




import com.example.payment_service.dtos.*;
import com.example.payment_service.dtos.ForSellInfo;
//import com.example.payment_service.entities.Payment;

import java.math.BigInteger;

public interface PaymentContractService {
//   public List<Immobillier> allImmobiliers();
//   public List<Immobillier> myImmobiliers();
//   public List<Immobillier> approvedImmobiliers();
//   public List<Immobillier> waitingImmobiliers();
//   public List<Immobillier> waitingChangeImmobiliers();
//   public List<Immobillier> forSellImmobiliers();
//   public Immobillier forSell(String _propId) throws Exception;
//   public String addNewUser(String _newUser) throws Exception;
//   public  Immobillier approveChangeOwnership(String _propId) throws Exception;
//   public StatusImmobilier approveProperty(String _propId) throws Exception;
//   public  String approveUsers(String _newUser) throws Exception;
//   public  String  changeOwnership(ChangeOwnershipInput changeOwnershipInput) throws Exception;
//   public Immobillier createProperty(ImmobilierInput immobilierInput ) throws Exception;
//   public void creatorAdmin() throws Exception;
//   public Immobillier getPropertyDetails(String _propId) throws Exception;
//   public void propOwnerChange(String param0) throws Exception;
   public BigInteger invest(PaymentInput userInput) throws Exception;

    BigInteger taxe(TaxeInput taxeInput) throws Exception;

    ForSellInfo forSell(ForSellInput forSellInput) throws Exception;

    String buy(BuyInput buyInput) throws Exception;

    Immobillier accepteChange(ApproveImmoInput approveImmoInput) throws Exception;

    String  refuseChange(ApproveImmoInput approveImmoInput) throws Exception;

    public BigInteger balanceOf() throws Exception;
   public BigInteger acceptePayment(String to, int ether) throws Exception;
   public BigInteger refusePayment(String to, int ether) throws Exception;
}
