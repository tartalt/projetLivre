package com.example.projetlivre.security.services;

import com.example.projetlivre.security.entites.Role;
import com.example.projetlivre.security.entites.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserDetailserviceImpl implements UserDetailsService {
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= accountService.loadUserByUsername(username);
        if (user==null)throw new UsernameNotFoundException("user not found");

        List<Role> roles = user.getRoles();
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());

        boolean can = user.isCan(); // Assuming the getter method is 'isCan'

        String id = user.getId(); // Assuming the getter method is 'getId'

        return new CustomUserDetails( user.getUsername(),
                user.getPassword(), authorities,can,id);
    }
}