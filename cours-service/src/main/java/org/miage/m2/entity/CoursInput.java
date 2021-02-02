package org.miage.m2.entity;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    private String statut;
    @NotNull
    private String acces;
    @NotNull
    private Long prix;
    private Set<String> episodesID;

}