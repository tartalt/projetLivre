package com.example.projetlivre;

import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.security.services.AccountService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjetLivreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetLivreApplication.class, args);
    }
//   @Bean
//    public CommandLineRunner commandLineRunner(AccountService accountService){
//        return args -> {
////            accountService.createUser("admin","123","123");
////            accountService.createUser("normal","123","123");
////            accountService.createRole("ADMIN");
////            accountService.createRole("USER");
////            accountService.createRole("CONFIRMED");
////            accountService.addRoleToUser("admin","ADMIN");
////            accountService.addRoleToUser("normal","USER");
////            accountService.addRoleToUser("normal","CONFIRMED");
////
////            accountService.createUser("test","123","123");
////                accountService.addRoleToUser("test","USER");
////                accountService.removeRoleFromUser("normal","USER");
////                accountService.addRoleToUser("normal","CONFIRMED");
//        };
//    }
}
