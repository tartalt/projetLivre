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
    // Getter method that adds the "ROLE_" prefix
    public String getRole() {
        // Check if the role already starts with "ROLE_"
        if (!role.startsWith("ROLE_")) {
            // If not, add the prefix
            return "ROLE_" + role;
        }
        return role;
    }
}
