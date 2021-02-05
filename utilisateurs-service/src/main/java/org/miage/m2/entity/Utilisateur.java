package org.miage.m2.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 8765432234567L;

    /*
    Utilisateur :
        • id
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
    @ElementCollection
    @CollectionTable(
        name="abonement",
        joinColumns=@JoinColumn(name="utilisateur_id")
    )
    @Column(name="id")
    @JsonProperty("abonements-id")
    private Set<String> abonementsID;
    
    public Utilisateur() {
	}

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

	public Utilisateur(String id, String nom, String prenom, String mail, String statut, Set<String> abonementsID) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.statut = statut;
		this.abonementsID = abonementsID;
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getNom() {
		return nom;
	}

	public final void setNom(String nom) {
		this.nom = nom;
	}

	public final String getPrenom() {
		return prenom;
	}

	public final void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public final String getMail() {
		return mail;
	}

	public final void setMail(String mail) {
		this.mail = mail;
	}

	public final String getStatut() {
		return statut;
	}

	public final void setStatut(String statut) {
		this.statut = statut;
	}

	public final Set<String> getAbonementsID() {
		return abonementsID;
	}

	public final void setAbonementsID(Set<String> abonementsID) {
		this.abonementsID = abonementsID;
	}
}