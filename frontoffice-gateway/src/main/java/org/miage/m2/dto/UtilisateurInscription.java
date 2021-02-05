package org.miage.m2.dto;

public class UtilisateurInscription {

    private String nom;
    private String prenom;
    private String mail;
    
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
}