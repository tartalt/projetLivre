package com.example.projetlivre.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "error titre")
    private String titre;
    @NotBlank(message = "error titre")
    private String auteur;
    @NotBlank(message = "error titre")
    private String description;
    private boolean disponible;
    @ManyToOne
    private Owner owner;



}
