package com.example.projetlivre.services;

import com.example.projetlivre.entities.Echange;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EchangeService {

    Echange saveEchange(Echange echange);
    Echange updateEchange(Echange echange);
    void deleteEchangeById(Long Id);
    void deleteAllEchange();
    Echange getEchangeByID(Long Id);
    List<Echange> getAllEchanges();
}
