package com.example.projetlivre.services;

import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.repositories.OwnerRepo;
import com.example.projetlivre.security.entites.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class OwnerServiceImpl implements OwnerService{
    private OwnerRepo ownerRepo;
    @Override
    public Owner saveOwner(Owner owner) {
        return ownerRepo.save(owner);
    }

    @Override
    public Owner updateOwner(Owner owner) {
        return ownerRepo.save(owner);
    }

    @Override
    public void deleteOwnerById(String Id) {
        ownerRepo.deleteById(Id);
    }

    @Override
    public void deleteAllOwners() {
        ownerRepo.deleteAll();
    }

    @Override
    public Owner getOwnerByID(String Id) {
        return ownerRepo.findById(Id).get();
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepo.findAll();
    }

    @Override
    public Page<Owner> getAllOwnersByPage(int page, int size) {
        return ownerRepo.findAll(PageRequest.of(page, size));
    }
}
