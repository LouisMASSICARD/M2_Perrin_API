package org.miage.m2.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.miage.m2.constants.EpisodeStatuts;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EpisodeInput {

	@NotNull
	@NotBlank
	private String cours_id;
    @NotNull
    private String concept;
    @NotNull
    @NotBlank
	private String href;
    @NotNull
    @ValidateEnumString(enumClass=EpisodeStatuts.class, message = "Ce statut n'existe pas (existants : ACTIF, SUPPRIME, etc.) .") 
    private String statut;
    
    public EpisodeInput(String cours_id, String concept, String href, String statut) {
    	this.cours_id = cours_id;
        this.concept = concept;
        this.href = href;
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "{" +
            " concept='" + getConcept() + "'" +
            ", href='" + getHref() + "'" +
            ", statut='" + getStatut() + "'" +
            "}";
    }
    
    public String getCours_id() {
        return this.cours_id;
    }
    
    public String getConcept() {
        return this.concept;
    }

    public String getHref() {
        return this.href;
    }

    public String getStatut() {
        return this.statut;
    }
}