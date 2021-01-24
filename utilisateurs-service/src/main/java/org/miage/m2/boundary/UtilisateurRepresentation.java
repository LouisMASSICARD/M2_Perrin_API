package org.miage.m2.boundary;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.miage.m2.entity.Utilisateur;
import org.miage.m2.entity.UtilisateurInput;
import org.miage.m2.entity.UtilisateurValidator;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.util.ReflectionUtils;


@RestController
@RequestMapping(value="/utilisateurs", produces=MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Utilisateur.class)
public class UtilisateurRepresentation {
    
    private final UtilisateurResource utilisateurRessource;
    private final UtilisateurValidator validator;

    public UtilisateurRepresentation(UtilisateurResource utilisateurResource, UtilisateurValidator utilisateurValidator) {
        this.utilisateurRessource = utilisateurResource;
        this.validator = utilisateurValidator;
    }

    // GET all
    @GetMapping
    public ResponseEntity<?> getAllIntervenants() {
        Iterable<Utilisateur> all = utilisateurRessource.findAll();
        return ResponseEntity.ok(utilisateurToResource(all));
    }

    // GET one
    @GetMapping(value="/{utilisateurID}")
    public ResponseEntity<?> getIntervenant(@PathVariable("utilisateurID") String id) {
        return Optional.ofNullable(utilisateurRessource.findById(id)).filter(Optional::isPresent)
        .map(utilisateur -> ResponseEntity.ok(utilisateurToResource(utilisateur.get(), true)))
        .orElse(ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid UtilisateurInput utilisateur) {
    	Utilisateur user = new Utilisateur(
    			UUID.randomUUID().toString(),
    			utilisateur.getNom(),
    			utilisateur.getPrenom(),
    			utilisateur.getMail(),
    			Utilisateur.UTILISATEUR_ACTIF);
    	Utilisateur saved = utilisateurRessource.save(user);
        URI location = linkTo(UtilisateurRepresentation.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // DELETE
    @DeleteMapping(value = "/{utilisateurID}")
    @Transactional
    public ResponseEntity<?> deleteIntervenant(@PathVariable("utilisateurID") String utilisateurID) {
        Optional<Utilisateur> utilisateur = utilisateurRessource.findById(utilisateurID);
        if (utilisateur.isPresent()) {
            utilisateurRessource.delete(utilisateur.get());
        }
        return ResponseEntity.noContent().build();
    }
    
    // PUT
    @PutMapping(value = "/{utilisateurID}")
    @Transactional
    public ResponseEntity<?> updateIntervenant(@RequestBody Utilisateur utilisateur, @PathVariable("utilisateurID") String utilisateurID) {
        Optional<Utilisateur> body = Optional.ofNullable(utilisateur);
        if (!body.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        if (!utilisateurRessource.existsById(utilisateurID)) {
            return ResponseEntity.notFound().build();
        }
        utilisateur.setId(utilisateurID);
        Utilisateur result = utilisateurRessource.save(utilisateur);
        return ResponseEntity.ok().build();
    }

    // PATCH
    @PatchMapping(value = "/{intervenantID}")
    @Transactional
    public ResponseEntity<?> updateIntervenantPartiel(@PathVariable("intervenantID") String utilisateurID, @RequestBody Map<Object, Object> fields) {
        Optional<Utilisateur> body = utilisateurRessource.findById(utilisateurID);
        if (body.isPresent()) {
        	Utilisateur utilisateur = body.get();
            fields.forEach((f, v) -> {
                Field field = ReflectionUtils.findField(Utilisateur.class, f.toString());
                field.setAccessible(true);
                ReflectionUtils.setField(field, utilisateur, v);
            });
            validator.validate(new UtilisateurInput(
            		utilisateur.getNom(),
            		utilisateur.getPrenom(),
                    utilisateur.getMail(),
                    utilisateur.getStatut()));
            utilisateur.setId(utilisateurID);
            utilisateurRessource.save(utilisateur);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    private CollectionModel<EntityModel<Utilisateur>> utilisateurToResource(Iterable<Utilisateur> utilisateurs) {
        Link selfLink = linkTo(methodOn(UtilisateurRepresentation.class).getAllIntervenants()).withSelfRel();
        List<EntityModel<Utilisateur>> intervenantResources = new ArrayList();
        utilisateurs.forEach(intervenant -> intervenantResources.add(utilisateurToResource(intervenant, false)));
        return  CollectionModel.of(intervenantResources, selfLink);
    }

    private EntityModel<Utilisateur> utilisateurToResource(Utilisateur utilisateur, Boolean collection) {
        var selfLink = linkTo(UtilisateurRepresentation.class).slash(utilisateur.getId()).withSelfRel();
        if (Boolean.TRUE.equals(collection)) {
            Link collectionLink = linkTo(methodOn(UtilisateurRepresentation.class).getAllIntervenants())
                    .withRel("collection");
            return EntityModel.of(utilisateur, selfLink, collectionLink);
        } else {
            return EntityModel.of(utilisateur, selfLink);
        }
    }

}
