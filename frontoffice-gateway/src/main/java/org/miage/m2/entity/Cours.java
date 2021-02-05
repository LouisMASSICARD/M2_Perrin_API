package org.miage.m2.entity;

import java.util.Set;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "cours")
public class Cours {

    private String id;
    private String nom;
    private String description;
    private String statut;
    private String acces;
    private Long prix;
    private Set<Long> episodesID;

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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return this.statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getAcces() {
        return this.acces;
    }

    public void setAcces(String acces) {
        this.acces = acces;
    }

    public Long getPrix() {
        return this.prix;
    }

    public void setPrix(Long prix) {
        this.prix = prix;
    }

    public Set<Long> getEpisodesID() {
        return this.episodesID;
    }

    public void setEpisodesID(Set<Long> episodesID) {
        this.episodesID = episodesID;
    }
}
