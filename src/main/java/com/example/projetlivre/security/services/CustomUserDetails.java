package com.example.projetlivre.security.services;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;


import java.util.Collection;


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
    private String Id;
    private boolean can;
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, boolean can, String Id) {
        super(username, password,true, true, true, true, authorities);
        this.Id = Id;
        this.can = can;


    }


}
