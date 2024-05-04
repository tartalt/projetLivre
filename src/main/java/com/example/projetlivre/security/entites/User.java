package com.example.projetlivre.security.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String Id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean can;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();
}
