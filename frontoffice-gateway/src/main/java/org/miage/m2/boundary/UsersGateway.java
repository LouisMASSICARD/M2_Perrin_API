package org.miage.m2.boundary;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.miage.m2.boundary.representation.CoursRepresentation;
import org.miage.m2.dto.CoursNamesDTO;
import org.miage.m2.dto.UtilisateurInscription;
import org.miage.m2.entity.Cours;
import org.miage.m2.entity.Utilisateur;
import org.miage.m2.validation.UtilisateurInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersGateway {

	private static final Logger LOG = Logger.getLogger(UsersGateway.class.getName());
	
    private final CoursReader coursReader;
    private final CoursWriter coursWriter;

    private final UtilisateurWriter utilisateurWriter;

    @Autowired
    public UsersGateway(
    		CoursReader coursReader, CoursWriter coursWriter,
    		UtilisateurWriter utilisateurWriter) {
    	this.coursReader = coursReader;
    	this.coursWriter = coursWriter;

    	this.utilisateurWriter = utilisateurWriter;
    }
    
    // INSCRIPTION    
    @PostMapping
    public ResponseEntity<?> inscription(@RequestBody UtilisateurInscription newUser) {
    	ResponseEntity<?> rep = this.utilisateurWriter.save(new UtilisateurInput(
    		  newUser.getNom(),
    		  newUser.getPrenom(),
    		  newUser.getMail(),
    		  Utilisateur.UTILISATEUR_STATUT_ACTIF,
    		  Collections.emptySet()));
       LOG.info("[UsersGateway] INSCRIPTION : " + rep);
//       return rep;
       return ResponseEntity.ok(rep.getBody(), );
    }
    
    // CONNEXION    
    @GetMapping(value="/{utilisateurID}")
    public ResponseEntity<?> connexion(@RequestBody UtilisateurInscription newUser) {
    	ResponseEntity<?> rep = this.utilisateurWriter.save(new UtilisateurInput(
    		  newUser.getNom(),
    		  newUser.getPrenom(),
    		  newUser.getMail(),
    		  Utilisateur.UTILISATEUR_STATUT_ACTIF,
    		  Collections.emptySet()));
       System.out.println("ResponseEntity<?> : " + rep);
       LOG.info("[UsersGateway] CONNEXION : " + rep);
       rep.getStatusCode();
       return rep;
    }

    @GetMapping
    public CollectionModel<EntityModel<Cours>> cours() {
        Collection<Cours> content = this.coursReader.getAllCours().getContent();
        List<EntityModel<Cours>> listeCours = new ArrayList<>();
        content.forEach(c -> listeCours.add(EntityModel.of(c)));
        return CollectionModel.of(listeCours);
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(method = RequestMethod.GET, value = "/noms")
    public CollectionModel<EntityModel<CoursNamesDTO>> noms() {
        Collection<Cours> content = this.coursReader.getAllCours().getContent();
        List<EntityModel<CoursNamesDTO>> listeCours = new ArrayList<>();
        content.forEach(c -> listeCours.add(EntityModel.of(new CoursNamesDTO(c.getId(), c.getNom()))));
        return CollectionModel.of(listeCours);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/description")
    public CollectionModel<EntityModel<Cours>> descriptions() {
        Collection<Cours> content = this.coursReader.getAllCours().getContent();
        List<EntityModel<Cours>> listeCours = new ArrayList<>();
        content.forEach(c -> listeCours.add(EntityModel.of(c)));
        return CollectionModel.of(listeCours);
    }

//    @PostMapping
//    public void insertCours(@RequestBody Cours cours) {
//      this.coursWriter.insertCours(cours);
//    }

    public CollectionModel<EntityModel<CoursNamesDTO>> fallback() {
        CoursNamesDTO c = new CoursNamesDTO();
        c.setNom("unavailable");
        List<EntityModel<CoursNamesDTO>> listeCours = new ArrayList<>();
        listeCours.add(EntityModel.of(c));
        return CollectionModel.of(listeCours);
    }
    
    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private Link linkToConnexion() {
    	return linkTo(methodOn(UsersGateway.class).connexion(null)).withRel("Connectez-vous");
//    	return linkTo(methodOn(UsersGateway.class).)
    }
//    private CollectionModel<EntityModel<Cours>> coursToResource(Iterable<Cours> cours) {
//        Link selfLink = linkTo(methodOn(UsersGateway.class).getAllCours(null, null)).withSelfRel();
//        List<EntityModel<Cours>> coursResources = new ArrayList<EntityModel<Cours>>();
//        cours.forEach(cour -> coursResources.add(coursToResource(cour, false)));
//        return  CollectionModel.of(coursResources, selfLink);
//    }
    
//    private EntityModel<Cours> coursToResource(Cours cours, Boolean collection) {
//        var selfLink = linkTo(CoursRepresentation.class).slash(cours.getId()).withSelfRel();
//        if (Boolean.TRUE.equals(collection)) {
//            Link collectionLink = linkTo(methodOn(CoursRepresentation.class).getAllCours(null, null)).withRel("collection");
//            return EntityModel.of(cours, selfLink, collectionLink);
//        } else {
//            return EntityModel.of(cours, selfLink);
//        }
//    }

}
