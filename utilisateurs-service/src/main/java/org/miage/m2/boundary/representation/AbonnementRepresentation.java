package org.miage.m2.boundary.representation;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.miage.m2.boundary.resource.AbonnementResource;
import org.miage.m2.entity.Abonnement;
import org.miage.m2.entity.Abonnement;
import org.miage.m2.validation.AbonnementInput;
import org.miage.m2.validation.AbonnementValidator;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
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
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.util.ReflectionUtils;


@RestController
@RequestMapping(value="/abonnements", produces=MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Abonnement.class)
public class AbonnementRepresentation {
    
    private final AbonnementResource abonnementRessource;
    private final AbonnementValidator validator;

    public AbonnementRepresentation(AbonnementResource abonnementResource, AbonnementValidator abonnementValidator) {
        this.abonnementRessource = abonnementResource;
        this.validator = abonnementValidator;
    }

    // GET all
    @GetMapping
    public ResponseEntity<?> getAllAbonnements() {
        Iterable<Abonnement> all = abonnementRessource.findAll();
        return ResponseEntity.ok(abonnementToResource(all));
    }

    // GET one
    @GetMapping(value="/{abonnementID}")
    public ResponseEntity<?> getAbonnement(@PathVariable("abonnementID") String id) {
        return Optional.ofNullable(abonnementRessource.findById(id)).filter(Optional::isPresent)
        .map(abonnement -> ResponseEntity.ok(abonnementToResource(abonnement.get(), true)))
        .orElse(ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid AbonnementInput abonnement) {
    	Abonnement user = new Abonnement(abonnement.getUtilisateur_id(), abonnement.getCours_id());
    	Abonnement saved = abonnementRessource.save(user);
        URI location = linkTo(AbonnementRepresentation.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    // DELETE
    @DeleteMapping(value = "/{abonnementID}")
    @Transactional
    public ResponseEntity<?> deleteAbonnement(@PathVariable("abonnementID") String abonnementID) {
        Optional<Abonnement> abonnement = abonnementRessource.findById(abonnementID);
        if (abonnement.isPresent()) {
        	Abonnement user = abonnement.get();
        	user.setStatut(Abonnement.ABONNEMENT_STATUT_SUPPRIME);
            Abonnement result = abonnementRessource.save(user);
            
            BodyBuilder body = ResponseEntity.status(204);
            ResponseEntity<?> response = body.body(abonnementToResource(result, true));
            return response;
        } else {
        	return ResponseEntity.notFound().build();
        }
    }
    
    // PUT
    @PutMapping(value = "/{abonnementID}")
    @Transactional
    public ResponseEntity<?> updateAbonnement(@RequestBody Abonnement abonnement, @PathVariable("abonnementID") String abonnementID) {
        Optional<Abonnement> body = Optional.ofNullable(abonnement);
        if (!body.isPresent()) {	
            return ResponseEntity.badRequest().build();
        }
        if (!abonnementRessource.existsById(abonnementID)) {
            return ResponseEntity.notFound().build();
        }
        abonnement.setId(abonnementID);
        Abonnement result = abonnementRessource.save(abonnement);
//        return ResponseEntity.ok().build();
        return ResponseEntity.ok(abonnementToResource(result, true));
    }

    // PATCH
    @PatchMapping(value = "/{abonnementID}")
    @Transactional
    public ResponseEntity<?> updateAbonnementPartiel(@PathVariable("abonnementID") String abonnementID, @RequestBody Map<Object, Object> fields) {
        Optional<Abonnement> body = abonnementRessource.findById(abonnementID);
        if (body.isPresent()) {
        	Abonnement abonnement = body.get();
            fields.forEach((f, v) -> {
                Field field = ReflectionUtils.findField(Abonnement.class, f.toString());
                field.setAccessible(true);
                ReflectionUtils.setField(field, abonnement, v);
            });
            validator.validate(new AbonnementInput(
            		abonnement.getUtilisateur_id(),
            		abonnement.getCours_id(),
                    abonnement.getStatut()));
            abonnement.setId(abonnementID);
//            abonnementRessource.save(abonnement);
//            return ResponseEntity.ok().build();
            Abonnement result = abonnementRessource.save(abonnement);
            return ResponseEntity.ok(abonnementToResource(result, true));
        }
        return ResponseEntity.notFound().build();
    }

    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private CollectionModel<EntityModel<Abonnement>> abonnementToResource(Iterable<Abonnement> abonnements) {
        Link selfLink = linkTo(methodOn(AbonnementRepresentation.class).getAllAbonnements()).withSelfRel();
        List<EntityModel<Abonnement>> abonnementResources = new ArrayList<EntityModel<Abonnement>>();
        abonnements.forEach(abonnement -> abonnementResources.add(abonnementToResource(abonnement, false)));
        return  CollectionModel.of(abonnementResources, selfLink);
    }

    private EntityModel<Abonnement> abonnementToResource(Abonnement abonnement, Boolean collection) {
        var selfLink = linkTo(AbonnementRepresentation.class).slash(abonnement.getId()).withSelfRel();
        if (Boolean.TRUE.equals(collection)) {
            Link collectionLink = linkTo(methodOn(AbonnementRepresentation.class).getAllAbonnements())
                    .withRel("collection");
            return EntityModel.of(abonnement, selfLink, collectionLink);
        } else {
            return EntityModel.of(abonnement, selfLink);
        }
    }
}
