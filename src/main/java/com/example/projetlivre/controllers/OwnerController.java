package com.example.projetlivre.controllers;

import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.security.entites.User;
import com.example.projetlivre.security.services.AccountService;
import com.example.projetlivre.security.services.CustomUserDetails;
import com.example.projetlivre.services.OwnerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@AllArgsConstructor
@Transactional
public class OwnerController {
    private final AccountService accountService;
    private OwnerService ownerService;
    @RequestMapping("/CreateOwner")
    public String createOwner(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (userDetails.isCan()){
            return "redirect:/ListeLivre";
        }
        return "CreateOwner";
    }
    @RequestMapping("/SaveOwner")
    public String saveOwner(@Valid Owner owner, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "CreateOwner";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String userId = userDetails.getId();
        User user = accountService.loadUserByUsername(userDetails.getUsername());
        owner.setUser(user);
        Owner saveOwner=ownerService.saveOwner(owner);

        user.setCan(true);
        accountService.removeRoleFromUser(user.getUsername(),"USER");
        accountService.addRoleToUser(user.getUsername(),"CONFIRMED");
        accountService.saveUser(user);
        return "redirect:/ListeLivre";
    }
    @RequestMapping("/OwnerList")
    public String Ownerlist(ModelMap modelMap,
                            @RequestParam(name="page",defaultValue = "0")int page,
                            @RequestParam(name="size",defaultValue = "5")int size) {
        Page<Owner> owners= ownerService.getAllOwnersByPage(page, size);
        modelMap.addAttribute("ownersVue",owners);
        modelMap.addAttribute("currentpage",page);
        modelMap.addAttribute("pages",new int[owners.getTotalPages()]);
        return "OwnerList";
    }
    @RequestMapping("/DeleteOwner")
    public String deleteOwner(@RequestParam("id") String id, ModelMap modelMap,
                              @RequestParam(name="page",defaultValue = "0")int page,
                              @RequestParam(name="size",defaultValue = "5")int size) {
        ownerService.deleteOwnerById(id);
        Page<Owner> owners= ownerService.getAllOwnersByPage(page, size);
        modelMap.addAttribute("ownersVue",owners);
        modelMap.addAttribute("currentpage",page);
        modelMap.addAttribute("pages",new int[owners.getTotalPages()]);

        return "Ownerlist";
    }

    @RequestMapping("/EditOwner")
    public String EditOwner(@RequestParam("id") String id, ModelMap modelMap,Authentication authentication) {
        Owner owner = ownerService.getOwnerByID(id);
        // Vérifier si l'utilisateur connecté est autorisé à modifier cet Owner
        if (!isAdmin(authentication) && !isOwner(authentication, owner)) {
            // Si l'Owner n'appartient pas à l'utilisateur connecté, renvoyer un message d'erreur ou rediriger vers une autre page
            // Vous pouvez personnaliser ce message selon vos besoins
            return "AccessDenied";
        }
        modelMap.addAttribute("ownersVue", owner);
        return "EditOwner";
    }
    private boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
    }

    private boolean isOwner(Authentication authentication, Owner owner) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return owner.getUser().getId().equals(userDetails.getId());
    }
    @RequestMapping("/UpdateOwner" )
    public String UpdateOwner(@ModelAttribute("ownerVue")Owner owner){
        ownerService.updateOwner(owner);
        return createOwner();
    }
}
