package com.example.projetlivre.controllers;

import com.example.projetlivre.entities.Livre;
import com.example.projetlivre.services.ServiceLivre;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class LivreController {

    private final ServiceLivre serviceLivre;

    @RequestMapping("/ListeLivre")
    public String livreList(ModelMap modelMap,
                            @RequestParam(name="page",defaultValue = "0")int page,
                            @RequestParam(name="size",defaultValue = "5")int size) {
        Page<Livre> livres = serviceLivre.getAllLivresByPage(page, size);
        modelMap.addAttribute("livres", livres);
        modelMap.addAttribute("currentpage",page);
        modelMap.addAttribute("pages",new int[livres.getTotalPages()]);
        return "ListeLivre"; // Assuming LivreList.html exists
    }

    @RequestMapping("/ajouterLivre")
    public String addLivre(ModelMap modelMap) {
        Livre livre = new Livre(); // Create a new Livre object for form binding
        modelMap.addAttribute("livre", livre);
        return "ajouterLivre"; // Assuming AddLivre.html exists
    }

    @RequestMapping("/saveLivre")
    public String saveLivre(@ModelAttribute("livre") Livre livre) {
        serviceLivre.saveLivre(livre);
        return "redirect:/ListeLivre"; // Redirect to LivreList after saving
    }

    @RequestMapping("/EditLivre")
    public String editLivre(@RequestParam("id") Long id, ModelMap modelMap) {
        Livre livre = serviceLivre.getLivreByID(id);
        modelMap.addAttribute("livre", livre);
        return "ListeLivre";
    }

    @RequestMapping("/updateLivre")
    public String updateLivre(@ModelAttribute("livre") Livre livre) {
        serviceLivre.updateLivre(livre);
        return "redirect:/ListeLivre"; // Redirect to LivreList after updating
    }

    @RequestMapping("/DeleteLivre")
    public String deleteLivre(@RequestParam("id") Long id) {
        serviceLivre.deleteLivreById(id);
        return "redirect:/ListeLivre"; // Redirect to LivreList after deleting
    }
}
