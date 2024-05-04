package com.example.projetlivre.security.services;

import com.example.projetlivre.security.entites.Role;
import com.example.projetlivre.security.entites.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailserviceImpl implements UserDetailsService {
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= accountService.loadUserByUsername(username);
        if (user==null)throw new UsernameNotFoundException("user not found");

        return org.springframework.security.core.userdetails.User
                .withUsername(username).password(user.getPassword())
                .roles(user.getRoles().stream().map(Role::getRole).toArray(String[]::new))
                .build();

    }
}