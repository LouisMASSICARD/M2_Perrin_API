package org.miage.m2.dto;

import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cours")
public class CoursNamesDTO {

    private String id;
    private String nom;

    public CoursNamesDTO() {
    }

    public CoursNamesDTO(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
