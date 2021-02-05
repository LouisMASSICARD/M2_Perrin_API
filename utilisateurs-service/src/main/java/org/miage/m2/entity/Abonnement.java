package org.miage.m2.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abonnement implements Serializable {

    private static final long serialVersionUID = 8765412334567L;

    /*
    Abonnement :
      • utilisateur_id
      • cours_id
      • statut => ACTIF || SUPPRIME
    */

    public final static String ABONNEMENT_STATUT_ACTIF = "ACTIF";
    public final static String ABONNEMENT_STATUT_SUPPRIME = "SUPPRIME";

    @Id
    private String id;
    private String utilisateur_id;
    private String cours_id;
    private String statut;


	public Abonnement(Abonnement utilisateur) {
        this.id = utilisateur.id;
        this.utilisateur_id = utilisateur.utilisateur_id;
        this.cours_id = utilisateur.cours_id;
        this.statut = utilisateur.statut;
    }

    public Abonnement(String utilisateur_id, String cours_id) {
    	this.id = UUID.randomUUID().toString();
    	this.utilisateur_id = utilisateur_id;
        this.cours_id = cours_id;
        this.statut = ABONNEMENT_STATUT_ACTIF;
    }
    
//    public Abonnement() {
//    }
//    
//	public Abonnement(String id, String utilisateur_id, String cours_id, String statut) {
//		this.id = id;
//		this.utilisateur_id = utilisateur_id;
//		this.cours_id = cours_id;
//		this.statut = statut;
//	}
//
//	public final String getId() {
//		return id;
//	}
//
//	public final void setId(String id) {
//		this.id = id;
//	}
//
//	public final String getUtilisateur_id() {
//		return utilisateur_id;
//	}
//
//	public final void setUtilisateur_id(String utilisateur_id) {
//		this.utilisateur_id = utilisateur_id;
//	}
//
//	public final String getCours_id() {
//		return cours_id;
//	}
//
//	public final void setCours_id(String cours_id) {
//		this.cours_id = cours_id;
//	}
//
//	public final String getStatut() {
//		return statut;
//	}
//
//	public final void setStatut(String statut) {
//		this.statut = statut;
//	}
}