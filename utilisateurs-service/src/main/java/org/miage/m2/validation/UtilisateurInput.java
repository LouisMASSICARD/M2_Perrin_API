package org.miage.m2.validation;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;

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
    private Set<String> abonnementsID;
	
    public UtilisateurInput() {
	}
	
	public UtilisateurInput(String nom, String prenom, String mail, Set<String> abonnementsID) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.abonnementsID = abonnementsID;
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
	
	public final Set<String> getAbonnementsID() {
		return abonnementsID;
	}
	public final void setAbonnementsID(Set<String> abonnementsID) {
		this.abonnementsID = abonnementsID;
	}
}