package org.miage.m2.boundary;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.miage.m2.dto.CoursNamesDTO;
import org.miage.m2.entity.Cours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cours")
public class CoursGateway {

    private final CoursReader coursReader;
    private final CoursWriter coursWriter;

    @Autowired
    public CoursGateway(CoursReader coursReader, CoursWriter coursWriter) {
        this.coursReader = coursReader;
        this.coursWriter = coursWriter;
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

    @PostMapping
    public void insertCours(@RequestBody Cours cours) {
      this.coursWriter.insertCours(cours);
    }

    public CollectionModel<EntityModel<CoursNamesDTO>> fallback() {
        CoursNamesDTO c = new CoursNamesDTO();
        c.setNom("unavailable");
        List<EntityModel<CoursNamesDTO>> listeCours = new ArrayList<>();
        listeCours.add(EntityModel.of(c));
        return CollectionModel.of(listeCours);
    }

}
