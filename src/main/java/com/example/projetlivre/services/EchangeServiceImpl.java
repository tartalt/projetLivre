package com.example.projetlivre.services;

import com.example.projetlivre.entities.Echange;
import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.enums.State;
import com.example.projetlivre.repositories.EchangeRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class EchangeServiceImpl implements EchangeService{
    private EchangeRepo echangeRepo;
    @Override
    public Echange saveEchange(Echange echange) {
        return echangeRepo.save(echange);
    }

    @Override
    public Echange updateEchange(Echange echange) {
        return echangeRepo.save(echange);
    }

    @Override
    public void deleteEchangeById(Long Id) {
        echangeRepo.deleteById(Id);
    }

    @Override
    public void deleteAllEchange() {
        echangeRepo.deleteAll();
    }

    @Override
    public Echange getEchangeById(Long id) {
        return echangeRepo.findById(id).get();
    }


    @Override
    public List<Echange> getAllEchanges() {
        return echangeRepo.findAll();
    }


    @Override
    public Page<Echange> getAllEchangesByPage(int page, int size) {
        return echangeRepo.findAll(PageRequest.of(page, size));
    }

    @Override
    public void accepterEchangeby2(Echange echange) {

    }

    @Override
    public void accepterEchangeby1(Echange echange) {

    }


    @Override
    public List<Echange> getAllEchangeByOwner2Encours(Owner owner,State state) {
        return echangeRepo.findAllByOwner2AndStateOrderByCreationDateDesc(owner, state);
    }
    @Override
    public List<Echange> getAllEchangeByOwner1Encours(Owner owner,State state) {
        return echangeRepo.findAllByOwner1AndStateOrderByCreationDateDesc(owner, state);
    }
    @Override
    public List<Echange> getAllEchangeByOwner2(Owner owner) {
        return echangeRepo.findAllByOwner2OrderByCreationDateDesc(owner);
    }
    @Override
    public List<Echange> getAllEchangeByOwner1(Owner owner) {
        return echangeRepo.findAllByOwner1OrderByCreationDateDesc(owner);
    }

    @Override
    public List<Echange> getAllEchnageByOwner(Owner owner) {
        return Stream.concat(echangeRepo.findAllByOwner1OrderByCreationDateDesc(owner).stream(), echangeRepo.findAllByOwner2OrderByCreationDateDesc(owner).stream()).collect(Collectors.toList());
    }

    @Override
    public Echange getEchangeByOwner1Livre2(Owner owner, Livre Livre) {
        return echangeRepo.findEchangeByOwner1AndLivre2(owner, Livre);
    }
    @Override
    public Echange getEchangeByOwner1Livre2ByState(Owner owner, Livre livre, State state) {
        return echangeRepo.findEchangeByOwner1AndLivre2AndState(owner, livre,state);
    }

    @Override
    public Echange getEchangeByOwner1Owner2(Owner owner, Owner owner1) {
        return echangeRepo.findEchangeByOwner1AndOwner2(owner, owner1);
    }


    @Override
    public Echange getEchangeByOwner1Owner2ByState(Owner owner, Owner owner2,State state) {
        return  echangeRepo.findEchangeByOwner1AndOwner2AndState(owner, owner2,state);
    }


}
