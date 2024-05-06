package com.example.projetlivre.controllers;

import com.example.projetlivre.entities.Echange;
import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.enums.State;
import com.example.projetlivre.repositories.EchangeRepo;
import com.example.projetlivre.security.services.CustomUserDetails;
import com.example.projetlivre.services.EchangeService;
import com.example.projetlivre.services.OwnerService;
import com.example.projetlivre.services.ServiceLivre;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class EchangeController {


    private final EchangeService echangeService;

    private final ServiceLivre serviceLivre;

    private final OwnerService ownerService;
    private final EchangeRepo echangeRepo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/MesDemandes")
    public String mesDemandes(ModelMap modelMap, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Owner owner = ownerService.getOwnerByID(userDetails.getId());
        List<Echange> echanges=echangeService.getAllEchnageByOwner(owner);
        List<Echange> demandesEnvoyees = echanges.stream()
                .filter(echange -> echange.getOwner1().equals(owner) && echange.getState().equals(State.New))
                .collect(Collectors.toList());

        List<Echange> demandesRecues = echanges.stream()
                .filter(echange -> echange.getOwner2().equals(owner) && echange.getState().equals(State.New))
                .collect(Collectors.toList());

        List<Echange> demandesArchivees = echanges.stream()
                .filter(echange -> echange.getState().equals(State.Finished))
                .collect(Collectors.toList());

        // Ajouter les listes à ModelMap pour les afficher dans la vue
        modelMap.addAttribute("demandesEnvoyees", demandesEnvoyees);
        modelMap.addAttribute("demandesRecues", demandesRecues);
        modelMap.addAttribute("demandesArchivees", demandesArchivees);
        return "MesDemandes"; // Assuming EchangeList.html exists
    }
    @GetMapping("/demander")
    public String proposeEchange(@RequestParam("id") Long id,
                                 Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Owner owner = ownerService.getOwnerByID(userDetails.getId());

        if(!owner.isPossede()){
            return "redirect:/ajouterLivre";
        }
        Livre livre = serviceLivre.getLivreByID(id);
        Echange echange = new Echange();
        Echange testechange=null;
        Echange testechange2=null;
        testechange  = echangeService.getEchangeByOwner1Owner2ByState(owner,livre.getOwner(),State.New);
        testechange2 = echangeService.getEchangeByOwner1Owner2ByState(livre.getOwner(),owner,State.New);
        if(!(testechange ==null) || !(testechange2==null)){
            return "redirect:/accessDenied";
        }
        echange.setLivre2(livre);
        echange.setCreationDate(new Date());
        echange.setOwner1(owner);
        echange.setOwner2(livre.getOwner());
        echangeService.saveEchange(echange);
        return "redirect:/ListeLivre"; // Assuming ProposerEchange.html exists
    }

   @RequestMapping("/choisir")
   public String validerEchange(@RequestParam("id")Long id,
                                @RequestParam("ide")Long ide,
                                Authentication authentication){
       CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
       Owner owner = ownerService.getOwnerByID(userDetails.getId());
       Livre livre1 =serviceLivre.getLivreByID(id);
       Echange echange=echangeService.getEchangeById(ide);
       Livre livre2=serviceLivre.getLivreByID(echange.getLivre2().getId());
       if (!(echange.getOwner1()==owner)){
           return "redirect:/ListeLivre";
       }
       echange.setLivre1(livre1);
       echange.setState(State.Finished);
       echange.setAcceptedDate(new Date());
       livre1.setDisponible(false);
       livre2.setDisponible(false);
       serviceLivre.saveLivre(livre1);
       serviceLivre.saveLivre(livre2);
       echangeService.saveEchange(echange);
       return "redirect:/ListeLivre";
   }
    @RequestMapping("/refuser")
    public String refuserEchange(@RequestParam("id")Long id,
                                 Authentication authentication){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Owner owner = ownerService.getOwnerByID(userDetails.getId());
        Echange echange=echangeService.getEchangeById(id);
        if (!(echange.getOwner2()==owner)){
            return "redirect:/ListeLivre";
        }
        echange.setState(State.Finished);
        echange.setRefusedDate(new Date());
        echangeService.saveEchange(echange);
        return "redirect:/ListeLivre";
    }
    @RequestMapping("/Annuler")
    public String annulerEchange(@RequestParam("id")Long id,
                                 Authentication authentication){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Owner owner = ownerService.getOwnerByID(userDetails.getId());
        Echange echange=echangeService.getEchangeById(id);
        if (!(echange.getOwner1()==owner)){
            return "redirect:/ListeLivre";
        }
        echange.setState(State.Finished);
        echange.setRefusedDate(new Date());
        echangeService.saveEchange(echange);
        return "redirect:/MesDemandes";
    }
}
