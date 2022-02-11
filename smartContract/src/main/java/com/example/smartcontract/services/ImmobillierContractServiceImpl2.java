package com.example.smartcontract.services;


import com.example.smartcontract.Repositories.ImmobillierRepository;
import com.example.smartcontract.SmartContractApplication;
import com.example.smartcontract.constant.LoadedContarct;

import com.example.smartcontract.contracts.test.ImmobillierContract;
import com.example.smartcontract.dtos.*;
import com.example.smartcontract.entities.Immobillier;
import com.example.smartcontract.restClient.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple3;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ImmobillierContractServiceImpl2 implements ImmobillierContractService {
    private final static String PRIVATE_KEY = "dc56417ae7de55a9f33e15394f0515f8b128d5d619c76256f6e51fe1fa7c5279";
    private final static BigInteger GAS_LIMIT = BigInteger.valueOf(6721975L);
    private final static BigInteger GAS_PRICE = BigInteger.valueOf(20000000000L);
    private final static String CONTRACT_ADDRESS = "0xe9af8b050eb1073d6fd3e49831e634a81e3d3233";
    private static final Logger log = LoggerFactory.getLogger(SmartContractApplication.class);


    @Autowired
    ImmobillierRepository immobillierRepository;
    @Autowired
    UserRestClient userRestClient;


    @Override
    public List<Immobillier> allImmobiliers() {
        return immobillierRepository.findAll();
    }

    @Override
    public List<Immobillier> myImmobiliers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserExistDto userExistDto = new UserExistDto();
        userExistDto.setUsername((String) auth.getPrincipal());
        UserResp userResp = userRestClient.getUser(userExistDto);
        return immobillierRepository.findAllByOwnerAddress(userResp.getAddress());
    }

    @Override
    public List<Immobillier> approvedImmobiliers() {
        return immobillierRepository.findAllByStatus(StatusImmobilier.APPROVED);
    }

    @Override
    public List<Immobillier> waitingImmobiliers() {
        return immobillierRepository.findAllByStatus(StatusImmobilier.WAITING);
    }

    @Override
    public List<Immobillier> waitingChangeImmobiliers() {
        return immobillierRepository.findAllByStatus(StatusImmobilier.WAITING_CHANGE);
    }

    @Override
    public List<Immobillier> forSellImmobiliers() {

        return immobillierRepository.findAllByForSell(true);
    }
    @Override
    public Immobillier forSell(String _propId) throws Exception {
        Immobillier immobillier = getPropertyDetails(_propId);
        if(immobillier.getStatus()==StatusImmobilier.WAITING ||immobillier.getStatus()==StatusImmobilier.REJECTED )
            throw new RuntimeException("not aproved yet !!");
        if (immobillier.isForSell()) {
            immobillier.setForSell(false);
        } else {
            immobillier.setForSell(true);
        }
        return immobillierRepository.save(immobillier);
    }
    @Override
    public Immobillier changePrice(ChangePriceInput priceInput) throws Exception {
        Immobillier immobillier = getPropertyDetails(priceInput.getImmo_id());
        immobillier.setPrice(priceInput.getNewprice());
        return immobillierRepository.save(immobillier);
    }




    @Override
    public String addNewUser(String _newUser) throws Exception {
        ImmobillierContract immobilierContract = loadContract(PRIVATE_KEY);
        immobilierContract.addNewAdmin(_newUser).send();
        return "user created . waiting for approvment ...";
    };

    @Override
    public Immobillier approveChangeOwnership(String _propId) throws Exception {
        ImmobillierContract immobilierContract = loadContract(PRIVATE_KEY);
        Immobillier immobillier = getPropertyDetails(_propId);
        System.out.println("last owner-----------:"+immobillier.getOwnerAddress());
        if(immobillier ==null) throw  new RuntimeException("immobilier doesnt exist !!!");
        immobilierContract.approveChangeOwnership(_propId).send();
        Tuple3<BigInteger, String, String> res = immobilierContract.getPropertyDetails(_propId).send();
        String add = res.component3();
        UserByAddressDto userByAddressDto = new UserByAddressDto(add);
        UserResp userResp = userRestClient.getUserByAddress(userByAddressDto);
        immobillier.setOwnerAddress(add);
        immobillier.setOwnerName(userResp.getUsername());
        immobillier.setForSell(false);
        immobillier.setStatus(StatusImmobilier.APPROVED);
        immobillierRepository.save(immobillier);
        return immobillierRepository.save(immobillier);
    }

    @Override
    public StatusImmobilier approveProperty(String _propId) throws Exception {
        ImmobillierContract immobilierContract = loadContract(PRIVATE_KEY);
        Immobillier immobillier = immobillierRepository.findImmobillierById(_propId);
        immobilierContract.approveProperty(_propId).send();
        immobillier.setStatus(StatusImmobilier.APPROVED);
        immobillierRepository.save(immobillier);
        return StatusImmobilier.APPROVED;
    }

    @Override
    public String approveUsers(String _newUser) throws Exception {
        ImmobillierContract immobilierContract = loadContract(PRIVATE_KEY);
        System.out.println("approve User "+ immobilierContract.approveUsers(_newUser).send());
        return "user Approved ";

    }

    @Override
    public String changeOwnership(ChangeOwnershipInput changeOwnershipInput) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserExistDto userExistDto = new UserExistDto();
        userExistDto.setUsername((String) auth.getPrincipal());
        System.out.println("++++++++++++"+userExistDto);
        UserResp userResp = userRestClient.getUser(userExistDto);
        ImmobillierContract immobilierContract = loadContract(changeOwnershipInput.getPrivateKey());
        Immobillier immobillier = getPropertyDetails(changeOwnershipInput.get_propId());
        if(immobillier ==null)  throw  new RuntimeException("immobilier doesnt exist !!!");
        if(!immobillier.getOwnerAddress().equalsIgnoreCase(userResp.getAddress()))  throw  new RuntimeException("immobilier is not yours !!!");
            immobilierContract.changeOwnership(changeOwnershipInput.get_propId(),
                    changeOwnershipInput.get_newOwner()).send();
            immobillier.setStatus(StatusImmobilier.WAITING_CHANGE);
            immobillierRepository.save(immobillier);
       return "wating for approvment .......";
    }
    @Override
    public String changeOwnership2(ChangeOwnershipInput2 changeOwnershipInput) throws Exception {

        UserExistDto userExistDto = new UserExistDto();
        userExistDto.setUsername(changeOwnershipInput.getUsername());
        System.out.println("++++++++++++"+userExistDto);
        UserResp userResp = userRestClient.getUser(userExistDto);
        ImmobillierContract immobilierContract = loadContract(changeOwnershipInput.getPrivateKey());
        Immobillier immobillier = getPropertyDetails(changeOwnershipInput.get_propId());
        if(immobillier ==null)  throw  new RuntimeException("immobilier doesnt exist !!!");
        if(!immobillier.getOwnerAddress().equalsIgnoreCase(userResp.getAddress()))  throw  new RuntimeException("immobilier is not yours !!!");
        System.out.println("++++++++++++"+changeOwnershipInput);
        immobilierContract.changeOwnership(changeOwnershipInput.get_propId(),
                changeOwnershipInput.get_newOwner()).send();
        immobillier.setStatus(StatusImmobilier.WAITING_ACCEPT_CHANGE);
        immobillierRepository.save(immobillier);
        return "wating for approvment .......";
    }





    @Transactional
    @Override
    public Immobillier createProperty(ImmobilierInput immobilierInput) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserExistDto userExistDto = new UserExistDto();
        userExistDto.setUsername((String) auth.getPrincipal());
        UserResp userResp = userRestClient.getUser(userExistDto);
        ImmobillierContract immobilierContract = loadContract(immobilierInput.getPrivateKey());
        System.out.println("saving ....."+immobilierInput.toString());
        Immobillier immobillier = new Immobillier(null,
                immobilierInput.get_price(),
                immobilierInput.getLocalisation(),
                immobilierInput.getCategory(),
                immobilierInput.getOwner(),
                userResp.getAddress(),
               false,
                immobilierInput.getDescription(),
                new Date(),
                StatusImmobilier.WAITING
                );
        System.out.println("saving .....------------------------------");
        Immobillier savedImmobillier= immobillierRepository.save(immobillier);
        System.out.println("-------------------"+savedImmobillier.getId()+"------------------");
        System.out.println("-------------------"+savedImmobillier.getOwnerAddress()+"------------------");
        System.out.println("------- add immobilier to block chain "+
                immobilierContract.createProperty(
                        savedImmobillier.getId(),
                        savedImmobillier.getId(),
                        savedImmobillier.getOwnerAddress()).send());
        return  savedImmobillier;
    }
//    @Override
//    public Immobillier createProperty(ImmobilierInput immobilierInput) throws Exception {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        UserExistDto userExistDto = new UserExistDto();
//        userExistDto.setUsername((String) auth.getPrincipal());
//        UserResp userResp = userRestClient.getUser(userExistDto);
//        ImmobillierContract immobilierContract = loadContract(immobilierInput.getPrivateKey());
//        System.out.println("saving ....."+immobilierInput.toString());
//        Immobillier immobillier = new Immobillier(null,
//                immobilierInput.get_price(),
//                immobilierInput.getLocalisation(),
//                immobilierInput.getCategory(),
//                immobilierInput.getOwner(),
//                immobilierInput.get_ownerAddress(),
//                immobilierInput.isForSell(),
//                immobilierInput.getDescription(),
//                new Date(),
//                StatusImmobilier.WAITING
//        );
//
//        Immobillier savedImmobillier= immobillierRepository.save(immobillier);
//        System.out.println("-------------------"+ savedImmobillier.getId()+"------------------");
//        System.out.println("------- add immobilier to block chain "+
//                immobilierContract.createProperty(
//                        savedImmobillier.getId(),
//                        savedImmobillier.getId(),
//                        savedImmobillier.getOwnerAddress()).send());
//        return  savedImmobillier;
//    }



    @Override
    public void creatorAdmin() throws Exception {
        ImmobillierContract immobilierContract = loadContract(PRIVATE_KEY);
        immobilierContract.creatorAdmin();
    }

    @Override
    public Immobillier getPropertyDetails(String _propId) throws Exception {
        ImmobillierContract immobilierContract = loadContract(PRIVATE_KEY);
       Tuple3<BigInteger, String, String> res= immobilierContract.getPropertyDetails(_propId).send();
        String add = res.component3();
       Immobillier immobillier = immobillierRepository.findImmobillierById(_propId);
       System.out.println(immobillier.toString());
       System.out.println("hnaaaaaaaaaaa : " + add + "\n hnaaaa 2 : " + res.component1()  + "\n hnaaaa 3 : " + res.component2() );
       if ( immobillier !=null && add.equalsIgnoreCase(immobillier.getOwnerAddress()) ){
           return immobillier;
       }
       else  throw  new RuntimeException("immobilier doesnt exist !!!");
    }

    @Override
    public void propOwnerChange(String param0) throws Exception {
        ImmobillierContract immobilierContract = loadContract(PRIVATE_KEY);
        immobilierContract.propOwnerChange(param0);
    }

    private ImmobillierContract loadContract(String privateKey) throws Exception {
        String node = "HTTP://0.0.0.0:7545";
        System.out.println("Connecting to Ethereum …");
        Web3j web3 = Web3j.build(new HttpService(node));
        System.out.println("Ethereum connected ");
        BigInteger privkey = new BigInteger(privateKey, 16);
        ECKeyPair ecKeyPair = ECKeyPair.create(privkey);
        Credentials credentials = Credentials.create(ecKeyPair);
        ImmobillierContract contract = ImmobillierContract.load(CONTRACT_ADDRESS, web3, credentials, GAS_PRICE, GAS_LIMIT);
        LoadedContarct.setLoadedContarct(contract);
        String  address_ = LoadedContarct.getLoadedContarct().getContractAddress();
        log.info("Smart contract deployed to address "+address_ );
        log.info("Creator  address "+ contract.creatorAdmin().send() );
        return contract;
    }
}
