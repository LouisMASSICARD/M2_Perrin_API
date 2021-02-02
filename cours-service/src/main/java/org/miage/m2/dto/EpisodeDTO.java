package org.miage.m2.dto;

public class EpisodeDTO {

	private String concept;
	private String href;
	private String statut;

	public EpisodeDTO() {
	}

	public EpisodeDTO(EpisodeDTO episode) {
		this.concept = episode.concept;
		this.href = episode.href;
		this.statut = episode.statut;
	}

	public EpisodeDTO(String concept, String href, String statut) {
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

	public String getConcept() {
		return this.concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}
}