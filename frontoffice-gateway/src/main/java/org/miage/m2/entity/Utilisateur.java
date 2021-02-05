package org.miage.m2.entity;

import java.util.Set;

public class Utilisateur {

	public final static String UTILISATEUR_STATUT_ACTIF = "ACTTIF";
    public final static String UTILISATEUR_STATUT_SUPPRIME = "SUPPRIME";

    private String id;
    private String nom;
    private String prenom;
    private String mail;
    private String statut;
    private Set<Long> abonnementsID;
    
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
	public final Set<Long> getAbonnementsID() {
		return abonnementsID;
	}
	public final void setAbonnementsID(Set<Long> abonnementsID) {
		this.abonnementsID = abonnementsID;
	} 
}