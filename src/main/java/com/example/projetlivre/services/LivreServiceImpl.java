package com.example.projetlivre.services;

import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.repositories.LivreRepo;
import com.example.projetlivre.repositories.OwnerRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LivreServiceImpl implements ServiceLivre {
    private final OwnerRepo ownerRepo;
    private LivreRepo livreRepo;
    @Override
    public Livre saveLivre(Livre livre) {
        livre.setDisponible(true);
        return livreRepo.save(livre);
    }

    @Override
    public Livre updateLivre(Livre livre) {
        return livreRepo.save(livre);
    }

    @Override
    public void deleteLivreById(Long Id) {
        livreRepo.deleteById(Id);
    }

    @Override
    public void deleteAllLivres() {
        livreRepo.deleteAll();
    }

    @Override
    public Livre getLivreByID(Long Id) {
        return livreRepo.findById(Id).get();
    }



    @Override
    public List<Livre> getAllLivres() {
        return livreRepo.findAll();

    }

    @Override
    public Page<Livre> getAllLivresByPage(int page, int size) {
        return livreRepo.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Livre> getAllLivresDisponiblesByOwner(Owner owner) {
        return livreRepo.findAllByOwnerAndDisponibleIsTrue(owner);
    }


}
