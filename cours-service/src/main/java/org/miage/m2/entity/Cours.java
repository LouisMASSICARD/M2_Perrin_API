package org.miage.m2.entity;

import java.io.Serializable;
// import java.util.Collection;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.JoinColumn;

import org.miage.m2.constants.CoursAcces;
import org.miage.m2.constants.CoursStatuts;

// import javax.persistence.OneToMany;
// import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
//@JsonIgnoreProperties({ "episodes" })
public class Cours implements Serializable {

    private static final long serialVersionUID = 1234567891234L;

    /*
    Cours :
        • id_cours
        • nom => theme
        • description => description_theme
        • statut = > gratuit || payant
        • prix
        • []id_episode => ensemble/liste d’épisodes vidéo
    */

    @Id
    private String id;
    private String nom;
    private String description;
    private String statut;
    private String acces;
    private Long prix;
    @ElementCollection
    @CollectionTable(
        name="episode",
        joinColumns=@JoinColumn(name="cours_id")
    )
    @Column(name="id")
    @JsonProperty("episodes-id")
    private Set<String> episodesID;

    public Cours() {
    }

    public Cours(Cours cours) {
    	this.id = cours.id;
    	this.nom = cours.nom;
    	this.description = cours.description;
        this.statut = cours.statut;
        this.acces = cours.acces;
        this.prix = cours.prix;
        this.episodesID = cours.episodesID;
    }
    
    public Cours(String nom, String description) {
        this.nom = nom;
        this.description = description;
        this.statut = CoursStatuts.ACTIF.toString();
        this.acces = CoursAcces.GARTUIT.toString();
        this.prix = 0L;
    }


    public Cours(String id, String nom, String description, String statut, String acces, Long prix, Set<String> episodesID) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.statut = statut;
        this.acces = acces;
        this.prix = prix;
        this.episodesID = episodesID;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nom='" + getNom() + "'" +
            ", description='" + getDescription() + "'" +
            ", statut='" + getStatut() + "'" +
            ", acces='" + getAcces() + "'" +
            ", prix='" + getPrix() + "'" +
            ", episodesID='" + getEpisodesID() + "'" +
            "}";
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

    public Set<String> getEpisodesID() {
        return this.episodesID;
    }

    public void setEpisodesID(Set<String> episodesID) {
        this.episodesID = episodesID;
    }
}