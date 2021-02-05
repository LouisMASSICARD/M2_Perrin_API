package org.miage.m2.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.miage.m2.constants.EpisodeStatuts;

@Entity
public class Episode implements Serializable {

	private static final long serialVersionUID = 1234567891234L;

	/*
	Episode/Video : 
		• id_episode
		• concept => concept (du cours) qui est présenté
		• href
	*/
	@Id
	private String id;
	private String concept;
	private String href;
	private String statut;


	public Episode() {
	}

	public Episode(Episode episode) {
		this.id = episode.id;
		this.concept = episode.concept;
		this.href = episode.href;
		this.statut = episode.statut;
	}

	public Episode(String concept, String href) {
		this.concept = concept;
		this.href = href;
		this.statut = EpisodeStatuts.ACTIF.toString();
	}

	public Episode(String id, String concept, String href, String statut) {
		this.id = id;
		this.concept = concept;
		this.href = href;
		this.statut = statut;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", concept='" + getConcept() + "'" +
			", href='" + getHref() + "'" +
			", statut='" + getStatut() + "'" +
			"}";
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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