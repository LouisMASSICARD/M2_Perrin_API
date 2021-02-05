package org.miage.m2.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbonnementInput {

    @NotNull
    @NotBlank
    private String utilisateur_id;
    @NotNull
    @NotBlank
    private String cours_id;
    private String statut;
}