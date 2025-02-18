package org.miage.m2.boundary.representation;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.miage.m2.entity.Episode;
import org.miage.m2.validation.EpisodeInput;
import org.miage.m2.validation.EpisodeValidator;
import org.miage.m2.boundary.resource.EpisodeResource;
import org.miage.m2.constants.EpisodeStatuts;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.util.ReflectionUtils;


@RestController
@RequestMapping(value="/episodes", produces=MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Episode.class)
public class EpisodeRepresentation {
    
    private static final Logger LOG = Logger.getLogger(EpisodeRepresentation.class.getName());

    private final EpisodeResource episodeRessource;
    private final EpisodeValidator validator;

    public EpisodeRepresentation(EpisodeResource episodeResource, EpisodeValidator episodeValidator) {
        this.episodeRessource = episodeResource;
        this.validator = episodeValidator;
    }

    // GET all
    @GetMapping
    public ResponseEntity<?> getAllEpisodes(@RequestParam(value = "statut", required = false,  defaultValue = "") String statut) {
        LOG.info("[Episodes] GET ALL");
        Iterable<Episode> all;
        if (statut.isBlank()) {
        	all = episodeRessource.findAll();			
		} else {
			if (! validator.validateSatut(statut) ) {
				return ResponseEntity.notFound().build();
			}
			LOG.info("[Cours] Statut : " + statut);
			all = episodeRessource.findByStatut(statut);
		}
        return ResponseEntity.ok(episodeToResource(all));
    }

    // GET one
    @GetMapping(value="/{episodeID}")
    public ResponseEntity<?> getEpisode(@PathVariable("episodeID") String episodeID) {
        LOG.info("[Episodes] GET ONE (episodeID) : " + episodeID);
        return Optional.ofNullable(episodeRessource.findById(episodeID)).filter(Optional::isPresent)
        .map(episode -> ResponseEntity.ok(episodeToResource(episode.get(), true)))
        .orElse(ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    @Transactional
    public ResponseEntity<?> saveEpisode(@RequestBody @Valid EpisodeInput episode) {
        LOG.info("[Episodes] POST : " + episode.toString());
        Episode user = new Episode(
                UUID.randomUUID().toString(),
                episode.getCours_id(),
    			episode.getConcept(),
    			episode.getHref(),
                episode.getStatut());
    	Episode saved = episodeRessource.save(user);
        URI location = linkTo(EpisodeRepresentation.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // DELETE
    @DeleteMapping(value = "/{episodeID}")
    @Transactional
    public ResponseEntity<?> deleteEpisode(@PathVariable("episodeID") String episodeID) {
        LOG.info("[Episodes] DELETE (episodeID) : " + episodeID);
        Optional<Episode> episode = episodeRessource.findById(episodeID);
        if (episode.isPresent()) {
        	Episode user = episode.get();
        	user.setStatut(EpisodeStatuts.SUPPRIME.toString());
            Episode result = episodeRessource.save(user);
            
            BodyBuilder body = ResponseEntity.status(204);
            ResponseEntity<?> response = body.body(episodeToResource(result, true));
            return response;
        } else {
        	return ResponseEntity.notFound().build();
        }
    }
    
    // PUT
    @PutMapping(value = "/{episodeID}")
    @Transactional
    public ResponseEntity<?> updateEpisode(@RequestBody Episode episode, @PathVariable("episodeID") String episodeID) {
        LOG.info("[Episodes] PUT (episodeID - episode) : " + episodeID + " - " + episode.toString());
        Optional<Episode> body = Optional.ofNullable(episode);
        if (!body.isPresent()) {	
            return ResponseEntity.badRequest().build();
        }
        if (!episodeRessource.existsById(episodeID)) {
            return ResponseEntity.notFound().build();
        }
        episode.setId(episodeID);
        Episode result = episodeRessource.save(episode);
        return ResponseEntity.ok(episodeToResource(result, true));
    }

    // PATCH
    @PatchMapping(value = "/{episodeID}")
    @Transactional
    public ResponseEntity<?> updateEpisodePartiel(@PathVariable("episodeID") String episodeID, @RequestBody Map<Object, Object> fields) {
        LOG.info("[Episodes] PATCH (episodeID - episode) : " + episodeID + " - " + fields);
        Optional<Episode> body = episodeRessource.findById(episodeID);
        if (body.isPresent()) {
        	Episode episode = body.get();
            fields.forEach((f, v) -> {
                Field field = ReflectionUtils.findField(Episode.class, f.toString());
                field.setAccessible(true);
                ReflectionUtils.setField(field, episode, v);
            });
            validator.validate(new EpisodeInput(
            		episode.getCours_id(),
            		episode.getConcept(),
        			episode.getHref(),
                    episode.getStatut()));
            episode.setId(episodeID);
            Episode result = episodeRessource.save(episode);
            return ResponseEntity.ok(episodeToResource(result, true));
        }
        return ResponseEntity.notFound().build();
    }

    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private CollectionModel<EntityModel<Episode>> episodeToResource(Iterable<Episode> episodes) {
        Link selfLink = linkTo(methodOn(EpisodeRepresentation.class).getAllEpisodes(null)).withSelfRel();
        List<EntityModel<Episode>> episodeResources = new ArrayList<EntityModel<Episode>>();
        episodes.forEach(episode -> episodeResources.add(episodeToResource(episode, false)));
        return  CollectionModel.of(episodeResources, selfLink);
    }

    private EntityModel<Episode> episodeToResource(Episode episode, Boolean collection) {
        var selfLink = linkTo(EpisodeRepresentation.class).slash(episode.getId()).withSelfRel();
        if (Boolean.TRUE.equals(collection)) {
            Link collectionLink = linkTo(methodOn(EpisodeRepresentation.class).getAllEpisodes(null))
                    .withRel("collection");
            return EntityModel.of(episode, selfLink, collectionLink);
        } else {
            return EntityModel.of(episode, selfLink);
        }
    }
}
