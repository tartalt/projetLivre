package com.example.projetlivre.controllers;

import com.example.projetlivre.entities.Owner;
import com.example.projetlivre.services.OwnerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import java.util.List;

@Controller
@AllArgsConstructor
public class OwnerController {
    private OwnerService ownerService;
    @RequestMapping("/CreateOwner")
    public String createOwner(){
        return "CreateOwner";
    }
    @RequestMapping("saveOwner")
    public String saveOwner(@Valid Owner owner, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "CreateOwner";
        }
        Owner saveOwner=ownerService.saveOwner(owner);
        return "CreateOwner";
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
    public String deleteOwner(@RequestParam("id") Long id, ModelMap modelMap,
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
    public String EditOwner(@RequestParam("id") Long id, ModelMap modelMap) {
        Owner owner = ownerService.getOwnerByID(id);
        modelMap.addAttribute("ownersVue", owner);
        return "EditOwner";
    }
    @RequestMapping("/UpdateOwner")
    public String UpdateOwner(@ModelAttribute("ownerVue")Owner owner){
        ownerService.updateOwner(owner);
        return createOwner();
    }
}
