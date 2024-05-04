package com.example.projetlivre.security.services;

import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.security.entites.Role;
import com.example.projetlivre.security.entites.User;
import com.example.projetlivre.security.repositories.RoleRepo;
import com.example.projetlivre.security.repositories.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
public class AccountServiceImpl  implements AccountService{
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RoleRepo roleRepo;
    @Override
    public User createUser(String username, String password, String passwordC) {

        User user=userRepo.findByUsername(username);
        if (user!=null)throw new RuntimeException("User exist");
        if(!password.equals(passwordC))throw new RuntimeException("Passwords do not match");
                user=User.builder()
                .username(username)
                .can(false)
                .Id(UUID.randomUUID().toString())
                .password(bCryptPasswordEncoder.encode(password))
                .build();
        return userRepo.save(user);

    }
    @Override
    public User saveUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public Role createRole(String role) {
        Role role1 =roleRepo.findByRole(role);
        if (role1!=null)throw new RuntimeException("Role exist");
                role1= Role.builder()
                .role(role)
                .build();
        return roleRepo.save(role1);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        User user=userRepo.findByUsername(username);
        Role role1 =roleRepo.findByRole(role);
        user.getRoles().add(role1);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        User user=userRepo.findByUsername(username);
        Role role1 =roleRepo.findByRole(role);
        user.getRoles().remove(role1);

    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }
}
