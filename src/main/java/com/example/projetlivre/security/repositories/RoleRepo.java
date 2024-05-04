package com.example.projetlivre.security.repositories;


import com.example.projetlivre.security.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,String> {
    Role findByRole(String role);
}
