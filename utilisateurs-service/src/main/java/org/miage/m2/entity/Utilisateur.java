package org.miage.m2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor      // ATTENTION, INDISPENSABLE POUR JPA !
@AllArgsConstructor
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 8765432234567L;

    /*
    Utilisateur :
        • id_user
        • nom
        • prenom
        • mail  => identification
        • statut => actif || supprime
    */

    public final static String UTILISATEUR_STATUT_ACTIF = "actif";
    public final static String UTILISATEUR_STATUT_SUPPRIME = "supprime";

    @Id
    private String id;
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String statut;

    public Utilisateur(Utilisateur utilisateur) {
        this.id = utilisateur.id;
        this.nom = utilisateur.nom;
        this.prenom = utilisateur.prenom;
        this.mail = utilisateur.mail;
        this.statut = utilisateur.statut;
    }

    public Utilisateur(String nom, String prenom, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.statut = UTILISATEUR_STATUT_ACTIF;
    }
}