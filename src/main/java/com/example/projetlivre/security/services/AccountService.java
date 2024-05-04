package com.example.projetlivre.security.services;

import com.example.projetlivre.security.entites.Role;
import com.example.projetlivre.security.entites.User;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    User createUser(String username,String password, String passwordC);
    Role createRole(String role);
    void addRoleToUser(String username, String role);
    void removeRoleFromUser(String username, String role);
    User loadUserByUsername(String username);
}