package com.example.projetlivre.entities;

import com.example.projetlivre.enums.State;
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

public class Echange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @PastOrPresent
    private Date creationDate;
    private State state= State.New;
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
