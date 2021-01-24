package org.miage.intervenantservice.boundary;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.miage.intervenantservice.entity.Intervenant;
import org.miage.intervenantservice.entity.IntervenantInput;
import org.miage.intervenantservice.entity.IntervenantValidator;
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
@RequestMapping(value="/intervenants", produces=MediaType.APPLICATION_JSON_VALUE)
@ExposesResourceFor(Intervenant.class)
public class UtilisateurRepresentation {
    
    private final UtilisateurResource ir;
    private final IntervenantValidator validator;

    public UtilisateurRepresentation(UtilisateurResource ir, IntervenantValidator ival) {
        this.ir = ir;
        this.validator = ival;
    }

    // GET all
    @GetMapping
    public ResponseEntity<?> getAllIntervenants() {
        Iterable<Intervenant> all = ir.findAll();
        return ResponseEntity.ok(intervenantToResource(all));
    }

    // GET one
    @GetMapping(value="/{intervenantId}")
    public ResponseEntity<?> getIntervenant(@PathVariable("intervenantId") String id) {
        return Optional.ofNullable(ir.findById(id)).filter(Optional::isPresent)
        .map(i -> ResponseEntity.ok(intervenantToResource(i.get(), true)))
        .orElse(ResponseEntity.notFound().build());
    }

    // POST
    @PostMapping
    @Transactional
    public ResponseEntity<?> save(@RequestBody @Valid IntervenantInput intervenant) {
        Intervenant i = new Intervenant(UUID.randomUUID().toString(),
        intervenant.getNom(), intervenant.getPrenom(), intervenant.getCommune(),
        intervenant.getCodepostal());
        Intervenant saved = ir.save(i);
        URI location = linkTo(UtilisateurRepresentation.class).slash(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

        // DELETE
        @DeleteMapping(value = "/{intervenantId}")
        @Transactional
        public ResponseEntity<?> deleteIntervenant(@PathVariable("intervenantId") String intervenantId) {
            Optional<Intervenant> intervenant = ir.findById(intervenantId);
            if (intervenant.isPresent()) {
                ir.delete(intervenant.get());
            }
            return ResponseEntity.noContent().build();
        }
    
      // PUT
      @PutMapping(value = "/{intervenantId}")
      @Transactional
      public ResponseEntity<?> updateIntervenant(@RequestBody Intervenant intervenant,
              @PathVariable("intervenantId") String intervenantId) {
          Optional<Intervenant> body = Optional.ofNullable(intervenant);
          if (!body.isPresent()) {
              return ResponseEntity.badRequest().build();
          }
          if (!ir.existsById(intervenantId)) {
              return ResponseEntity.notFound().build();
          }
          intervenant.setId(intervenantId);
          Intervenant result = ir.save(intervenant);
          return ResponseEntity.ok().build();
      }

    // PATCH
    @PatchMapping(value = "/{intervenantId}")
    @Transactional
    public ResponseEntity<?> updateIntervenantPartiel(@PathVariable("intervenantId") String intervenantId,
            @RequestBody Map<Object, Object> fields) {
        Optional<Intervenant> body = ir.findById(intervenantId);
        if (body.isPresent()) {
            Intervenant intervenant = body.get();
            fields.forEach((f, v) -> {
                Field field = ReflectionUtils.findField(Intervenant.class, f.toString());
                field.setAccessible(true);
                ReflectionUtils.setField(field, intervenant, v);
            });
            validator.validate(new IntervenantInput(intervenant.getNom(), intervenant.getPrenom(),
                    intervenant.getCommune(), intervenant.getCodepostal()));
            intervenant.setId(intervenantId);
            ir.save(intervenant);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

      private CollectionModel<EntityModel<Intervenant>> intervenantToResource(Iterable<Intervenant> intervenants) {
        Link selfLink = linkTo(methodOn(UtilisateurRepresentation.class).getAllIntervenants()).withSelfRel();
        List<EntityModel<Intervenant>> intervenantResources = new ArrayList();
        intervenants.forEach(intervenant -> intervenantResources.add(intervenantToResource(intervenant, false)));
        return  CollectionModel.of(intervenantResources, selfLink);
    }

    private EntityModel<Intervenant> intervenantToResource(Intervenant intervenant, Boolean collection) {
        var selfLink = linkTo(UtilisateurRepresentation.class).slash(intervenant.getId()).withSelfRel();
        if (Boolean.TRUE.equals(collection)) {
            Link collectionLink = linkTo(methodOn(UtilisateurRepresentation.class).getAllIntervenants())
                    .withRel("collection");
            return EntityModel.of(intervenant, selfLink, collectionLink);
        } else {
            return EntityModel.of(intervenant, selfLink);
        }
    }

}
