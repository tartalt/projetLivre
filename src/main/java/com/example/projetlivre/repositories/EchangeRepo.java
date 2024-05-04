package com.example.projetlivre.repositories;

import com.example.projetlivre.entities.Echange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EchangeRepo extends JpaRepository<Echange, Long> {
}
