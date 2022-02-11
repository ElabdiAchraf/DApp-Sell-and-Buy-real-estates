package com.example.jwt.services;

import com.example.jwt.clientRest.ContractRestClient;
import com.example.jwt.dtos.*;
import com.example.jwt.entities.AppRole;
import com.example.jwt.entities.AppUser;
import com.example.jwt.entities.Code;
import com.example.jwt.repositories.AppRoleRepository;
import com.example.jwt.repositories.AppUserRepository;
import com.example.jwt.security.MailService;
import com.example.jwt.utils.UserCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional

public class AccountServiceImpl implements AccountService {


    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    MailService mailService;
    @Autowired
    ContractRestClient contractRestClient;



    @Override
    public AppUser addUser(String username, String password, String comfirmPassword ,String address) {
        String _code = UserCode.getCode();
        AppUser user = appUserRepository.findAppUserByUsername(username);
        if(user!=null) throw new RuntimeException("the user already exist");
        if(!password.equals(comfirmPassword)) throw  new RuntimeException("please comfirme your password");
        AppUser appUser = new AppUser();
        mailService.sendCodeByEmail(new MailDTO(username,_code));
        Code code =new Code();
        code.setCode(_code);
        appUser.setUsername(username);
        appUser.setCode(code);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.setActived(0);
        appUser.setAddress(address);
        appUserRepository.save(appUser);
        UserInput userInput =new UserInput();
        userInput.setUser(address);
       String s= contractRestClient.addUserToContarct(userInput);
       System.out.println("--------------------------------------------------------"+s);
        addRoleToUser(username,"USER");
        return appUser;
    }

    @Override
    public UserActive getUserActive(String username, String password) {
        AppUser appUser =appUserRepository.findAppUserByUsername(username);

        String enPassword=appUser.getPassword();
        boolean result=bCryptPasswordEncoder.matches(password,enPassword);
        UserActive userActive =new UserActive();
        if(!result){
        userActive.setActive(-1);
        }else {
            userActive.setActive(appUser.getActived());
        }
        return userActive;

    }

    @Override
    public AppRole saveRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public AppUser loadUserbyUsername(String username) {
        return appUserRepository.findAppUserByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = appUserRepository.findAppUserByUsername(username);
        AppRole appRole =appRoleRepository.findByRoleName(role);
        appUser.getRoles().add(appRole);

    }

    @Override
    public AccountResponse activeAccount(ActiveAccount activeAccount) {
        AppUser appUser =appUserRepository.findAppUserByUsername(activeAccount.getUsername());
        AccountResponse accountResponse  = new AccountResponse();
        if (appUser.getCode().getCode().equals(activeAccount.getCode())){
            UserInput userInput =new UserInput();
            userInput.setUser(appUser.getAddress());
            String s= contractRestClient.approveUserToContarct(userInput);
            System.out.println("--------------------------------------------------------"+s);
            appUser.setActived(1);
            updateUser(appUser);
            accountResponse.setResult(1);
        }
        else {
            accountResponse.setResult(0);
        }
        return accountResponse;
    }

    @Override
    public AccountResponse resetPassword(ResetPassword resetPassword) {

            AppUser user =appUserRepository.findAppUserByUsername(resetPassword.getUsername());
            AccountResponse accountResponse  = new AccountResponse();
            if (user!=null){
                String code =UserCode.getCode();
                MailDTO mailDTO =new MailDTO(resetPassword.getUsername(),code);
                mailService.sendCodeByEmail(mailDTO);
                user.getCode().setCode(code);
                updateUser(user);
                accountResponse.setResult(1);
            }
            else {
                accountResponse.setResult(0);
            }
        return accountResponse;
    }

    @Override
    public UserResp getUser(String username) {
        AppUser appUser= appUserRepository.findAppUserByUsername(username);
        UserResp userResp = new UserResp(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getActived(),
                appUser.getAddress()
        );
        return  userResp;
    }

    @Override
    public UserResp getUserByAddress(String address) {
        AppUser appUser= appUserRepository.findAppUserByAddress(address);
        UserResp userResp = new UserResp(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getActived(),
                appUser.getAddress()
        );
        return  userResp;
    }

    public void updateUser(AppUser appUser){
        appUserRepository.save(appUser);
    }
}
