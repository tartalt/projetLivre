package com.example.projetlivre.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Echange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @PastOrPresent
    private Date creationDate;
    private boolean accepted;
    @PastOrPresent
    private Date acceptedDate;
    @ManyToOne
    private Owner owner1;

    @ManyToOne
    private Owner owner2;

    @ManyToOne
    private Livre livre1;

    @ManyToOne
    private Livre livre2;
}
