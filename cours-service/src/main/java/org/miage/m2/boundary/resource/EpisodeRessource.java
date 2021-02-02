package org.miage.m2.boundary.resource;

import org.miage.m2.entity.Episode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="episode", path="episode")
public interface EpisodeRessource extends CrudRepository<Episode, String> {
}