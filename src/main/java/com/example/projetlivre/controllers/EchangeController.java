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
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;

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
        modelMap.addAttribute("echanges",echanges);
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
        testechange  = echangeService.getEchangeByOwner1Owner2(owner,livre.getOwner());
        testechange2 = echangeService.getEchangeByOwner1Owner2(livre.getOwner(),owner);
        if(!(testechange ==null) && !(testechange2==null)){
            return "redirect:/accessDenied";
        }
        echange.setLivre2(livre);
        echange.setCreationDate(new Date());
        // Get the logged-in owner
        echange.setOwner1(owner);
        echange.setOwner2(livre.getOwner());
        serviceLivre.saveLivre(livre);
        echangeService.saveEchange(echange);
        return "redirect:/ListeLivre"; // Assuming ProposerEchange.html exists
    }

   @RequestMapping("/choisir")
   public String validerEchange(@RequestParam("id")Long id,
                                @RequestParam("ide")Long ide,
                                Authentication authentication){
       CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
       Owner owner = ownerService.getOwnerByID(userDetails.getId());
       Livre livre=serviceLivre.getLivreByID(id);
       Echange echange=echangeService.getEchangeById(ide);
       if (!(echange.getOwner1()==owner)){
           return "redirect:/ListeLivre";
       }
       echange.setLivre1(livre);
       echange.setState(State.Finished);
       echange.setAcceptedDate(new Date());
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
}
