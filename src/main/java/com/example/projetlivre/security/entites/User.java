package com.example.projetlivre.security.entites;

import com.example.projetlivre.entities.Owner;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;


import java.util.ArrayList;
import java.util.List;
@Transactional
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @Column(unique = true)
    private String Id;
    @Column(unique = true)
    private String username;
    private String password;
    private boolean can;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles=new ArrayList<>();
}
