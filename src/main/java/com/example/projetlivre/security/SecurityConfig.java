package com.example.projetlivre.security;

import com.example.projetlivre.security.services.UserDetailserviceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig  {

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(
                        authCustomizer ->authCustomizer.requestMatchers("/OwnerList","/DeleteOwner","/UpdateOwner","EditOwner").hasRole("ADMIN")
                                .requestMatchers("/DeleteLivre","/saveOwner","/ajouterLivre","/EditLivre","/saveLivre","/ListeLivre").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/DeleteLivre","/ajouterLivre","/EditLivre","/saveLivre","/ListeLivre","/EditOwner").hasRole("CONFIRMED")

                                .anyRequest().authenticated()
                )
                .exceptionHandling(e->e.accessDeniedPage("/accessDenied"))
                .build();
    }
    }


