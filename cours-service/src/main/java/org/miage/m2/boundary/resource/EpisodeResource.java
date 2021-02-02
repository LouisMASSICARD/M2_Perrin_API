package org.miage.m2.boundary.resource;

import org.miage.m2.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeResource extends JpaRepository<Episode, String> {
}
