package org.miage.m2.boundary;

import org.miage.m2.entity.Cours;
import org.miage.m2.entity.Episode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("cours-service")
public interface CoursReader {

    @RequestMapping(method = RequestMethod.GET, value = "/cours")
    CollectionModel<Cours> getAllCours();

    @RequestMapping(method = RequestMethod.GET, value = "/episodes")
    CollectionModel<Episode> getAllEpisodes();
}

