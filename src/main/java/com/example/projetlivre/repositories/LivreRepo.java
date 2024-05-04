package com.example.projetlivre.repositories;

import com.example.projetlivre.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivreRepo extends JpaRepository<Livre, Long> {
}
