package com.example.projetlivre.repositories;

import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.entities.Owner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepo extends JpaRepository<Livre, Long> {
    List<Livre> findAllByOwnerAndDisponibleIsTrue(Owner owner);
    List<Livre> findAllByOwner(Owner owner);
    Page<Livre> findAllByDisponibleTrue(PageRequest pageable);

}
