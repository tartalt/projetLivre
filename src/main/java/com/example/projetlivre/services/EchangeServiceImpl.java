package com.example.projetlivre.services;

import com.example.projetlivre.entities.Echange;
import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.enums.State;
import com.example.projetlivre.repositories.EchangeRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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
        echange.setState(State.Accepted_by_2);
    }
    @Override
    public void accepterEchangeby1(Echange echange) {
        echange.setState(State.Full_accepted);
    }

}
