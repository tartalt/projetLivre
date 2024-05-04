package com.example.projetlivre.services;


import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.entities.Owner;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceLivre {
    Livre saveLivre(Livre livre);
    Livre updateLivre(Livre livre);
    void deleteLivreById(Long Id);
    void deleteAllLivres();
    Livre getLivreByID(Long Id);
    List<Livre> getAllLivres();
    Page<Livre> getAllLivresByPage(int page, int size);
}
