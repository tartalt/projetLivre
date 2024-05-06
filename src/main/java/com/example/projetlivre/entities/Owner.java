package com.example.projetlivre.entities;


import com.example.projetlivre.security.entites.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @OneToOne
    @MapsId
    private User user;
    @NotBlank(message = "error firstName")
    private String firstName;
    @NotBlank(message = "error lastname")
    private String lastName;
    @NotBlank(message = "error email")
    @Email
    private String email;
    @NotBlank(message = "error phone")
    private String phone;
    @NotBlank(message = "error phone")
    private String address;
    @Column
    private boolean possede;

}
