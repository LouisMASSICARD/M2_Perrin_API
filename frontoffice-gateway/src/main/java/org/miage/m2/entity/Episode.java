package org.miage.m2.entity;

import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "episode")
public class Episode  {

	private String id;
	private String cours_id;
	private String concept;
	private String href;
	private String statut;

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

	public final String getCours_id() {
		return cours_id;
	}

	public final void setCours_id(String cours_id) {
		this.cours_id = cours_id;
	}
}