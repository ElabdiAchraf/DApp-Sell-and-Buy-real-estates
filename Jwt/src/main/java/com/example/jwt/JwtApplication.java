package com.example.jwt;

import com.example.jwt.entities.AppRole;
import com.example.jwt.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import java.util.stream.Stream;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }
     @Bean
     CommandLineRunner start(AccountService accountService){
        return args -> {
//            accountService.saveRole(new AppRole(null,"USER"));
//            accountService.saveRole(new AppRole(null,"ADMIN"));
//            Stream.of("admin").forEach(un->{
//                accountService.addUser(un,"1234","1234","0x7E697899EB507AF3B7eB3D404814bEC6e2531430");
//            });
//            accountService.addRoleToUser("admin","ADMIN");
//            Stream.of("user1").forEach(un->{
//                accountService.addUser(un,"1234","1234","0x773c0261e62e49548D719B00e96bBABb49b9888A");
//            });
//            accountService.addRoleToUser("user1","USER");
        };
    }
    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }

}
