package com.example.projetlivre.security.services;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;


import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends User {



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public boolean isCan() {
        return can;
    }

    public void setCan(boolean can) {
        this.can = can;
    }

    // you can add custom field here, example field 'name'
    private String Id;
    private boolean can;
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities ,boolean can, String Id) {
        super(username, password, authorities);
        this.Id=Id;
        this.can=can;


    }





    // don't forget getter-setter

}