package com.example.projetlivre.services;

import com.example.projetlivre.entities.Owner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {
    Owner saveOwner(Owner owner);
    Owner updateOwner(Owner owner);
    void deleteOwnerById(String Id);
    void deleteAllOwners();
    Owner getOwnerByID(String Id);
    List<Owner> getAllOwners();
    Page<Owner> getAllOwnersByPage(int page,int size);
}
