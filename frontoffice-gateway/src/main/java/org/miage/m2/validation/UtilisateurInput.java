package org.miage.m2.validation;

import java.util.Set;

public class UtilisateurInput {

    private String nom;
    private String prenom;
    private String mail;
    private String statut;
    private Set<Object> abonnementsID;
    
	
	public UtilisateurInput(String nom, String prenom, String mail, String utilisateurStatutActif, Set<Object> emptySet) {
		this.nom = nom;
	    this.prenom = prenom;
	    this.mail = mail;
	    this.statut = utilisateurStatutActif;
	    this.abonnementsID = emptySet;
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
	public final Set<Object> getAbonnementsID() {
		return abonnementsID;
	}
	public final void setAbonnementsID(Set<Object> abonnementsID) {
		this.abonnementsID = abonnementsID;
	} 
}