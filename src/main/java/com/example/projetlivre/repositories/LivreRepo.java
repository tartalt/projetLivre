package com.example.projetlivre.repositories;

import com.example.projetlivre.entities.Echange;
import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.security.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepo extends JpaRepository<Livre, Long> {
    List<Livre> findAllByOwnerAndDisponibleIsTrue(Owner owner);
    List<Livre> findAllByOwner(Owner owner);

}
