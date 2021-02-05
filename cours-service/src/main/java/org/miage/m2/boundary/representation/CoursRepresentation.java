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
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.miage.m2.entity.Cours;
import org.miage.m2.validation.CoursInput;
import org.miage.m2.validation.CoursValidator;
import org.miage.m2.boundary.client.EpisodeClient;
import org.miage.m2.boundary.resource.CoursResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.util.ReflectionUtils;


@RestController
@RequestMapping(value="/cours", produces=MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Cours.class)
public class CoursRepresentation {
    
    private static final Logger LOG = Logger.getLogger(CoursRepresentation.class.getName());

    @Autowired
    EpisodeClient client;
    
    private final CoursResource coursRessource;
    private final CoursValidator validator;

    public CoursRepresentation(CoursResource coursResource, CoursValidator coursValidator) {
        this.coursRessource = coursResource;
        this.validator = coursValidator;
    }

    // GET all
    @GetMapping
    public ResponseEntity<?> getAllCours() {
        LOG.info("[Cours] GET ALL");
        Iterable<Cours> all = coursRessource.findAll();
        // for (Cours cours : all) {
        //     LOG.warning("[Cours] : " + cours.toString());
        // }
        return ResponseEntity.ok(coursToResource(all));
    }

    // GET one
    @GetMapping(value="/{coursID}")
    public ResponseEntity<?> getCours(@PathVariable("coursID") String coursID) {
        LOG.info("[Cours] GET ONE (coursID) : " + coursID);
        return Optional.ofNullable(coursRessource.findById(coursID)).filter(Optional::isPresent)
        .map(cours -> ResponseEntity.ok(coursToResource(cours.get(), true)))
        .orElse(ResponseEntity.notFound().build());
    }
    
    // POST
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid CoursInput cours) {
        LOG.info("[Cours] POST : " + cours.toString());
        Cours user = new Cours(
                UUID.randomUUID().toString(),
    			cours.getNom(),
    			cours.getDescription(),
                cours.getStatut(),
    			cours.getAcces(),
    			cours.getPrix(),
                cours.getEpisodesID());
    	Cours saved = coursRessource.save(user);
        URI location = linkTo(CoursRepresentation.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // DELETE
    @DeleteMapping(value = "/{coursID}")
    @Transactional
    public ResponseEntity<?> deleteCours(@PathVariable("coursID") String coursID) {
        LOG.info("[Cours] DELETE (coursID) : " + coursID);
        Optional<Cours> cours = coursRessource.findById(coursID);
        if (cours.isPresent()) {
//            coursRessource.delete(cours.get());
        	Cours user = cours.get();
        	System.out.println(user);
        	user.setStatut(Cours.COURS_STATUT_SUPPRIME);
        	System.out.println(user);
            Cours result = coursRessource.save(user);
            System.out.println(result);
//            return ResponseEntity.ok(coursToResource(result, true));
            
//            return ResponseEntity.noContent().build();
//            return ResponseEntity.status(201).build().of(coursToResource(result, true));
            
//            System.out.println(ResponseEntity.status(HttpStatus.NO_CONTENT));
//            System.out.println(ResponseEntity.status(HttpStatus.NO_CONTENT).body(coursToResource(result, true)));
            
            BodyBuilder body = ResponseEntity.status(204);
            ResponseEntity<?> response = body.body(coursToResource(result, true));
            
            System.out.println(body);
            System.out.println(response);
            
            return response;
            
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(coursToResource(result, true));

        } else {
        	return ResponseEntity.notFound().build();
        }
    }
    
    // PUT
    @PutMapping(value = "/{coursID}")
    @Transactional
    public ResponseEntity<?> updateCours(@RequestBody Cours cours, @PathVariable("coursID") String coursID) {
        LOG.info("[Cours] PUT (coursID - cours) : " + coursID + " - " + cours.toString());
        Optional<Cours> body = Optional.ofNullable(cours);
        if (!body.isPresent()) {	
            return ResponseEntity.badRequest().build();
        }
        if (!coursRessource.existsById(coursID)) {
            return ResponseEntity.notFound().build();
        }
        cours.setId(coursID);
        Cours result = coursRessource.save(cours);
        //        return ResponseEntity.ok().build();
        return ResponseEntity.ok(coursToResource(result, true));
    }
    
    // PATCH
    @PatchMapping(value = "/{coursID}")
    @Transactional
    public ResponseEntity<?> updateCoursPartiel(@PathVariable("coursID") String coursID, @RequestBody Map<Object, Object> fields) {
        LOG.info("[Cours] PATCH (coursID - cours) : " + coursID + " - " + fields);
        Optional<Cours> body = coursRessource.findById(coursID);
        if (body.isPresent()) {
        	Cours cours = body.get();
            fields.forEach((f, v) -> {
                Field field = ReflectionUtils.findField(Cours.class, f.toString());
                field.setAccessible(true);
                ReflectionUtils.setField(field, cours, v);
            });
            validator.validate(new CoursInput(
                cours.getNom(),
    			cours.getDescription(),
                cours.getStatut(),
    			cours.getAcces(),
    			cours.getPrix(),
                cours.getEpisodesID()));
            cours.setId(coursID);
//            coursRessource.save(cours);
//            return ResponseEntity.ok().build();
            Cours result = coursRessource.save(cours);
            return ResponseEntity.ok(coursToResource(result, true));
        }
        return ResponseEntity.notFound().build();
    }

    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private CollectionModel<EntityModel<Cours>> coursToResource(Iterable<Cours> cours) {
        Link selfLink = linkTo(methodOn(CoursRepresentation.class).getAllCours()).withSelfRel();
        // LOG.severe("Collection selfLink : " + selfLink);
        List<EntityModel<Cours>> coursResources = new ArrayList<EntityModel<Cours>>();
        cours.forEach(cour -> coursResources.add(coursToResource(cour, false)));
        return  CollectionModel.of(coursResources, selfLink);
    }
    
    private EntityModel<Cours> coursToResource(Cours cours, Boolean collection) {
        var selfLink = linkTo(CoursRepresentation.class).slash(cours.getId()).withSelfRel();
        // LOG.warning("Cours selfLink : " + selfLink);
        if (Boolean.TRUE.equals(collection)) {
            Link collectionLink = linkTo(methodOn(CoursRepresentation.class).getAllCours()).withRel("collection");
            return EntityModel.of(cours, selfLink, collectionLink);
        } else {
            // LOG.info("Cours : " + EntityModel.of(cours, selfLink));
            return EntityModel.of(cours, selfLink);
        }
    }
}
