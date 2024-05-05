package com.example.projetlivre.controllers;

import com.example.projetlivre.entities.Echange;
import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.security.services.CustomUserDetails;
import com.example.projetlivre.services.EchangeService;
import com.example.projetlivre.services.OwnerService;
import com.example.projetlivre.services.ServiceLivre;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@AllArgsConstructor
public class EchangeController {

    private final EchangeService echangeService;
    private final ServiceLivre serviceLivre;
    private final OwnerService ownerService;

    @GetMapping("/EchangeList")
    public String echangeList(ModelMap modelMap,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Echange> echanges = echangeService.getAllEchangesByPage(page, size);
        modelMap.addAttribute("echanges", echanges);
        modelMap.addAttribute("currentpage", page);
        modelMap.addAttribute("pages", new int[echanges.getTotalPages()]);
        return "EchangeList"; // Assuming EchangeList.html exists
    }

    @GetMapping("/demander")
    public String proposeEchange(@RequestParam("id") Long id, Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Owner owner = ownerService.getOwnerByID(userDetails.getId());
        if(!owner.isPossede()){
            return "redirect:/ajouterLivre";
        }
        Livre livre = serviceLivre.getLivreByID(id);
        Echange echange = new Echange();
        //dans ma logique la personne qui demande un livre est le owner1
        //donc le owner2 possede le livre2 dans l'echange
        echange.setLivre2(livre);
        echange.setCreationDate(new Date());
        // Get the logged-in owner
        echange.setOwner1(owner);
        echange.setOwner2(livre.getOwner());
        serviceLivre.saveLivre(livre);
        echangeService.saveEchange(echange);
        return "redirect:/ListeLivre"; // Assuming ProposerEchange.html exists
    }

    @PostMapping("/saveEchange")
    public String saveEchange(@Valid @ModelAttribute("echange") Echange echange, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ProposerEchange"; // Return to the form if there are validation errors
        }

        echangeService.saveEchange(echange);
        return "redirect:/EchangeList"; // Redirect to EchangeList after saving
    }

    @GetMapping("/DetailsEchange/{echangeId}")
    public String detailsEchange(@PathVariable("echangeId") Long echangeId, ModelMap modelMap) {
        Echange echange = echangeService.getEchangeById(echangeId);
        modelMap.addAttribute("echange", echange);
        return "DetailsEchange"; // Assuming DetailsEchange.html exists
    }

    @GetMapping("/accepterEchange/{echangeId}")
    public String accepterEchange(@PathVariable("echangeId") Long echangeId, Authentication authentication) {
        Echange echange = echangeService.getEchangeById(echangeId);

        // Get the logged-in owner
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Owner owner = ownerService.getOwnerByID(userDetails.getId());

        // Check if the logged-in owner is the receiver of the proposed exchange
        if (!echange.getOwner2().getId().equals(owner.getId())) {
            // If not the receiver, return an error or redirect to another page
            return "AccessDenied";
        }
        echangeService.accepterEchangeby2(echange);
        return "redirect:/EchangeList"; // Redirect to EchangeList after accepting
    }

}
