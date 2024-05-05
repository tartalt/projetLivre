package com.example.projetlivre.services;

import com.example.projetlivre.entities.Echange;

import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.enums.State;
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
    List<Echange> getAllEchangeByOwner1Encours(Owner owner,State state);
    List<Echange> getAllEchangeByOwner2Encours(Owner owner,State state);
    List<Echange> getAllEchnageByOwner(Owner owner);
    Echange getEchangeByOwner1Livre2(Owner owner, Livre Livre);
    Echange getEchangeByOwner1Owner2ByState(Owner owner, Owner owner2,State state);
    List<Echange> getAllEchangeByOwner1(Owner owner);
    List<Echange> getAllEchangeByOwner2(Owner owner);
   Echange getEchangeByOwner1Livre2ByState(Owner owner, Livre livre, State state);

    Echange getEchangeByOwner1Owner2(Owner owner, Owner owner1);
}
