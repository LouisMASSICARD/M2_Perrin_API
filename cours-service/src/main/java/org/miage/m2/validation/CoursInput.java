package org.miage.m2.validation;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.miage.m2.constants.CoursAcces;
import org.miage.m2.constants.CoursStatuts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursInput {

    @NotNull
    @NotBlank
    private String nom;
    @NotNull
    private String description;
    @NotNull
    @ValidateEnumString(enumClass=CoursStatuts.class, message = "Ce statut n'existe pas (existants : ACTIF, SUPPRIME, etc.) .") 
    private String statut;
    @NotNull
    @ValidateEnumString(enumClass=CoursAcces.class, message = "Ce type d'accès n'existe pas (existants : GRATUIT, PAYANT, etc.) .") 
    private String acces;
    @NotNull
    private Long prix;
    private Set<String> episodesID;

    @Override
    public String toString() {
        return "{" +
            " nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", statut='" + getStatut() + "'" +
            ", acces='" + getAcces() + "'" +
            ", prix='" + getPrix() + "'" +
            ", episodesID='" + getEpisodesID() + "'" +
            "}";
    }
}