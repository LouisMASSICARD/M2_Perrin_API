package org.miage.m2.validation;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurInput {

    @NotNull
    @NotBlank
    private String nom;
    @NotNull
    @NotBlank
    private String prenom;
    // @Pattern(regexp = "^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$")
    @NotNull
    @NotBlank
    @Email
    private String mail;
}