package org.miage.m2.boundary.client;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.Optional;

import org.miage.m2.boundary.resource.EpisodeRessource;
import org.miage.m2.entity.Episode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author louis
 *
 */
@Component
public class EpisodeClient {
	
	@Autowired
	EpisodeRessource episodeResource;
    
    @HystrixCommand(fallbackMethod = "fallback")
    public Episode get(String id) {
//        return restClient.get(id);
    	Optional<Episode> episode = episodeResource.findById(id);
    	if (episode.isPresent()) {
			return episode.get();
		} else {
			return new Episode("non trouvé en base", "pas de lien");
		}
//    	return Optional.ofNullable(episodeRessource.findById(id)).filter(Optional::isPresent)
//    	        .map(episode -> ResponseEntity.ok(episodeToResource(episode.get(), true)))
//    	        .orElse(ResponseEntity.notFound().build());
    }

    public Episode fallback(String id) {
        return new Episode("non disponible", "pas de lien");
    }
    
//    @Autowired
//    IntervenantRestClient restClient;
//    
//    @HystrixCommand(fallbackMethod = "fallback")
//    public Intervenant get(String id) {
//        return restClient.get(id);
//    }
//
//    public Intervenant fallback(String id) {
//        return new Intervenant("non disponible");
//    }
}
