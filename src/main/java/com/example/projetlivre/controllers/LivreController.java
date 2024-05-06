package com.example.projetlivre.controllers;
import com.example.projetlivre.entities.Echange;
import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.enums.State;
import com.example.projetlivre.security.services.CustomUserDetails;
import com.example.projetlivre.services.EchangeService;
import com.example.projetlivre.services.OwnerService;
import com.example.projetlivre.services.ServiceLivre;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class LivreController {

    private final ServiceLivre serviceLivre;
    private final OwnerService ownerService;
    private final EchangeService echangeService;
    @RequestMapping("/ListeLivre")
    public String livreList(ModelMap modelMap,
                            @RequestParam(name="page",defaultValue = "0")int page,
                            @RequestParam(name="size",defaultValue = "5")int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (!(((CustomUserDetails) authentication.getPrincipal()).isCan())){
            return "redirect:/CreateOwner";
        }
        Owner owner=ownerService.getOwnerByID(userDetails.getId());
        List<Echange> Echange1 = null;
        Page<Livre> livres = serviceLivre.getAllLivresByPage(page, size);
        Echange1=trouveEchangeP(livres,owner);

        modelMap.addAttribute("livres", livres);
        modelMap.addAttribute("currentpage",page);
        modelMap.addAttribute("pages",new int[livres.getTotalPages()]);
        modelMap.addAttribute("echange",Echange1);
        return "ListeLivre";
    }

    @RequestMapping("/Bibliotheque")
    public String Bibliotheque(@RequestParam("id") String id,
                               ModelMap modelMap,
                               @RequestParam(name = "page", defaultValue = "0") int page,
                               @RequestParam(name = "size", defaultValue = "5") int size) {
        // Récupérer l'owner à partir de l'ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Owner owner2 = ownerService.getOwnerByID(userDetails.getId());
        Owner owner1 = ownerService.getOwnerByID(id);
        Echange Echange1 = null;
        // Vérifier si l'owner existe
        List<Livre> livres = serviceLivre.getAllLivresDisponiblesByOwner(owner1);
        Echange1=trouveEchange(livres,owner2);
        int start = page * size;
        int end = Math.min(start + size, livres.size());
        List<Livre> livresPage = livres.subList(start, end);

            if (owner1 == null) {
                // Gérer le cas où l'owner n'existe pas
                return "redirect:/ListeLivre"; // page à afficher en cas de propriétaire non trouvé
            }
            // Ajouter les attributs à ModelMap
            modelMap.addAttribute("livres", livresPage);
            modelMap.addAttribute("currentpage", page);
            modelMap.addAttribute("pages", new int[(int) Math.ceil((double) livres.size() / size)]);
            modelMap.addAttribute("size", size);
            modelMap.addAttribute("id", id);
            modelMap.addAttribute("echange",Echange1);// passer l'ID de l'owner à la vue

            return "Bibliotheque"; // Assuming LivreList.html exists
    }
    Echange trouveEchange(List<Livre> livres,Owner owner2) {
        Echange Echange1 = null;
        for (Livre livre : livres) {
            Echange1 = echangeService.getEchangeByOwner1Livre2ByState(owner2, livre,State.New);
            if (!(Echange1 == null)) {
                return Echange1;
            }
        }
        return Echange1;
    }
    List<Echange> trouveEchangeP(Page<Livre> livres,Owner owner2) {
        List<Echange> Echange3 = new ArrayList<>();
        for (Livre livre : livres) {
            Echange Echange1 = null;
            Echange Echange2 = null;
            Echange1 = echangeService.getEchangeByOwner1Owner2ByState(owner2, livre.getOwner(), State.New);
            Echange2 = echangeService.getEchangeByOwner1Owner2ByState(livre.getOwner(), owner2, State.New);
            if (!(Echange1 == null) || !(Echange2 == null)) {
                if (!(Echange1 == null)) {
                    Echange3.add(Echange1);
                } else if (!(Echange2 == null)) {
                    Echange3.add(Echange2);
                }
            }
        }
        return Echange3;
    }
    @RequestMapping("/ajouterLivre")
    public String addLivre(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Owner owner = ownerService.getOwnerByID(userDetails.getId());
        if(!userDetails.isCan()){
            return "redirect:/CreateOwner";
        }
        Livre livre = new Livre();
        if (!owner.isPossede()){
            owner.setPossede(true);
            ownerService.updateOwner(owner);
        }
        modelMap.addAttribute("livre", livre);
        return "ajouterLivre"; // Assuming AddLivre.html exists
    }

    @RequestMapping("/saveLivre")
    public String saveLivre(@Valid @ModelAttribute("livre") Livre livre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ajouterLivre"; // Retourner vers le formulaire s'il y a des erreurs de validation
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Owner owner = ownerService.getOwnerByID(userDetails.getId());
        owner.setPossede(true);
        livre.setOwner(owner);
        livre.setDisponible(true);
        serviceLivre.saveLivre(livre);
        ownerService.saveOwner(owner);
        return "redirect:/ListeLivre"; // Redirect to LivreList after saving
    }

    @RequestMapping("/EditLivre")
    public String editLivre(@RequestParam("id") Long id, ModelMap modelMap) {
        Livre livre = serviceLivre.getLivreByID(id);
        modelMap.addAttribute("livre", livre);
        return "EditLivre";
    }

    @RequestMapping("/updateLivre")
    public String updateLivre(@ModelAttribute("livre") Livre livre) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Owner owner = ownerService.getOwnerByID(userDetails.getId());
        livre.setOwner(owner);

        serviceLivre.updateLivre(livre);
        return "redirect:/ListeLivre"; // Redirect to LivreList after updating
    }

    @RequestMapping("/DeleteLivre")
    public String deleteLivre(@RequestParam("id") Long id) {
        serviceLivre.deleteLivreById(id);
        return "redirect:/ListeLivre"; // Redirect to LivreList after deleting
    }


}
