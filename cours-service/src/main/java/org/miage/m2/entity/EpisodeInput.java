package org.miage.m2.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EpisodeInput {

    @NotNull
    private String concept;
    @NotNull
    @NotBlank
	private String href;
    @NotNull
    private String statut;
    
    public EpisodeInput(String concept, String href, String statut) {
        this.concept = concept;
        this.href = href;
        this.statut = statut;
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