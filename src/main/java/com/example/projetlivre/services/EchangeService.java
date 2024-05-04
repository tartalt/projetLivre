package com.example.projetlivre.services;

import com.example.projetlivre.entities.Echange;

import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.entities.Owner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EchangeService {

    Echange saveEchange(Echange echange);
    Echange updateEchange(Echange echange);
    void deleteEchangeById(Long Id);
    void deleteAllEchange();
    Echange getEchangeById(Long id);
    List<Echange> getAllEchanges();
    Page<Echange> getAllEchangesByPage(int page, int size);
    void accepterEchangeby2(Echange echange);
    void accepterEchangeby1(Echange echange);
}
