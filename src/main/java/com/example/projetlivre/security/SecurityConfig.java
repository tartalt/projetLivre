package com.example.projetlivre.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
                        authCustomizer ->authCustomizer.requestMatchers("/OwnerList","/DeleteOwner").hasRole("ADMIN")
                                .requestMatchers("/DeleteLivre","/ajouterLivre","/EditLivre","/saveLivre","/EditOwner","/UpdateOwner").hasAnyRole("ADMIN","CONFIRMED")
                                .requestMatchers("/CreateOwner","/UpdateOwner").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/saveOwner","/ListeLivre").authenticated()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(e->e.accessDeniedPage("/accessDenied"))
                .build();
    }
    }


