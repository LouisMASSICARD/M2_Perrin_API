package org.miage.m2.entity;

public class Intervenant {
    private String nom;
    private String prenom;
    private String commune;
    private Long codepostal;

    public Intervenant() {
    }

    public Intervenant(String nom, String prenom, String commune, Long codepostal) {
        this.nom = nom;
        this.prenom = prenom;
        this.commune = commune;
        this.codepostal = codepostal;
    }
    
    public Intervenant(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public Long getCodepostal() {
        return codepostal;
    }

    public void setCodepostal(Long codepostal) {
        this.codepostal = codepostal;
    }

    
}
