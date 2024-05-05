package com.example.projetlivre;

import com.example.projetlivre.entities.Echange;
import com.example.projetlivre.repositories.EchangeRepo;
import com.example.projetlivre.services.EchangeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AllArgsConstructor

class ProjetLivreApplicationTests {


    private EchangeService echangeService;


    @Test
    void contextLoads() {

        Echange echanges= echangeService.getEchangeById(17L);
        System.out.println();
    }

}
