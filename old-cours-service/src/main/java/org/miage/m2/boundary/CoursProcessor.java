package org.miage.m2.boundary;

import java.util.List;
import java.util.stream.Collectors;
import org.miage.m2.entity.Cours;
import org.miage.m2.entity.Detail;
import org.miage.m2.entity.Episode;
//import org.miage.m2.entity.Intervenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Service;

/**
 * @author louis
 *
 */
@Service
public class CoursProcessor implements RepresentationModelProcessor<EntityModel<? extends Cours>> {

	@Autowired
	EpisodeClient client;
//	EpisodeRepresentation episodeClient;

	@Override
    public EntityModel<Detail> process(EntityModel<? extends Cours> resource) {
        Cours cours = resource.getContent();
        List<Episode> intervenants = cours
                .getEpisodesID()
                .stream()
//                .map(episodeRessource::findById::get)
                .map(client::get)
                .collect(Collectors.toList());
        return EntityModel.of(new Detail(cours,intervenants),resource.getLinks());
    }
	    
//    @Autowired
//    IntervenantClient client;
//
//    @Override
//    public EntityModel<Detail> process(EntityModel<? extends Cours> resource) {
//        Cours cours = resource.getContent();
//        List<Intervenant> intervenants = cours
//                .getIntervenantsId()
//                .stream()
//                .map(client::get)
//                .collect(Collectors.toList());
//        return EntityModel.of(new Detail(cours,intervenants),resource.getLinks());
//    }
}

