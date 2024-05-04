package com.example.projetlivre.security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/")
    public String home(){
        return "redirect:/ListeLivre";
    }
    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "/accessDenied";
    }
}
