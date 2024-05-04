package com.example.projetlivre.services;

import com.example.projetlivre.entities.Echange;
import com.example.projetlivre.repositories.EchangeRepo;
import lombok.AllArgsConstructor;
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
    public Echange getEchangeByID(Long Id) {
        return echangeRepo.findById(Id).get();
    }

    @Override
    public List<Echange> getAllEchanges() {
        return echangeRepo.findAll();
    }
}
