package com.example.projetlivre.repositories;

import com.example.projetlivre.entities.Echange;
import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EchangeRepo extends JpaRepository<Echange, Long> {
    List<Echange> findAllByOwner1OrderByCreationDateDesc(Owner owner);
    List<Echange> findAllByOwner2OrderByCreationDateDesc(Owner owner);
    List<Echange> findAllByOwner1AndAcceptedDateIsNullOrderByCreationDateDesc(Owner owner);
    List<Echange> findAllByOwner2AndAcceptedDateIsNullOrderByCreationDateDesc(Owner owner);
    Echange findEchangeByOwner1AndLivre2AndState(Owner owner1, Livre livre2, State state);
    Echange findEchangeByOwner1AndLivre2(Owner owner1, Livre livre2);
    Echange findEchangeByOwner1AndOwner2AndState(Owner owner1, Owner owner2, State state);
    List<Echange> findAllByOwner2AndStateOrderByCreationDateDesc(Owner owner, State state);
    List<Echange> findAllByOwner1AndStateOrderByCreationDateDesc(Owner owner, State state);
    List<Echange> findEchangeByOwner1AndLivre2AndState(Owner owner, State state,Livre livre);
    Echange findEchangeByOwner1AndOwner2(Owner owner,Owner owner2);



}
