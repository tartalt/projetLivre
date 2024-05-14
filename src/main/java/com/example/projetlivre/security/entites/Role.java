package com.example.projetlivre.security.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    private String role;

    public String getRole() {

        if (!role.startsWith("ROLE_")) {

            return "ROLE_" + role;
        }
        return role;
    }
}
