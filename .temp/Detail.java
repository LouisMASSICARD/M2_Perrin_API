package org.miage.m2.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

// import org.springframework.hateoas.server.core.Relation;

/**
 * @author louis
 *
 */
// @Relation(collectionRelation="cours")
public class Detail extends Cours {

	private static final long serialVersionUID = 1L;
    
    // @JsonProperty("espisodes")
	private final List<Episode> episodes;

    public Detail(Cours cours, List<Episode> episodes) {
        super(cours);
        this.episodes = episodes;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }
}
