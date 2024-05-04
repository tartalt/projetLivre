package com.example.projetlivre.security.services;

import com.example.projetlivre.security.entites.Role;
import com.example.projetlivre.security.entites.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailserviceImpl implements UserDetailsService {
    private AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user= accountService.loadUserByUsername(username);
//        if (user==null)throw new UsernameNotFoundException("user not found");
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(username).password(user.getPassword())
//                .roles(user.getRoles().stream().map(Role::getRole).toArray(String[]::new))
//                .build();
//
//    }
//}
        User user= accountService.loadUserByUsername(username);
        if (user==null)throw new UsernameNotFoundException("user not found");

        List<Role> roles = user.getRoles();
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());

        boolean can = user.isCan(); // Assuming the getter method is 'isCan'

        String id = user.getId(); // Assuming the getter method is 'getId'

        return new CustomUserDetails( user.getUsername(),
                user.getPassword(), authorities,can,id);
    }
}