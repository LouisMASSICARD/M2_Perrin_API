package org.miage.m2.entity;

public class Abonnement {
    public final static String ABONNEMENT_STATUT_ACTIF = "ACTIF";
    public final static String ABONNEMENT_STATUT_SUPPRIME = "SUPPRIME";

    private String id;
    private String utilisateur_id;
    private String cours_id;
    private String statut;
    
	public final String getId() {
		return id;
	}
	public final void setId(String id) {
		this.id = id;
	}
	public final String getUtilisateur_id() {
		return utilisateur_id;
	}
	public final void setUtilisateur_id(String utilisateur_id) {
		this.utilisateur_id = utilisateur_id;
	}
	public final String getCours_id() {
		return cours_id;
	}
	public final void setCours_id(String cours_id) {
		this.cours_id = cours_id;
	}
	public final String getStatut() {
		return statut;
	}
	public final void setStatut(String statut) {
		this.statut = statut;
	}
}