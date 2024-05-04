package com.example.projetlivre.repositories;

import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.security.entites.User;
import jakarta.persistence.IdClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, String> {
}
