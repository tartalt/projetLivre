package com.example.projetlivre.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
