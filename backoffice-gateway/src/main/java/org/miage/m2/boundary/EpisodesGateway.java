package org.miage.m2.boundary;

// import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// import org.miage.m2.dto.CoursNamesDTO;
import org.miage.m2.entity.Episode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/episodes")
public class EpisodesGateway {

    private final CoursReader coursReader;
    private final CoursWriter coursWriter;

    @Autowired
    public EpisodesGateway(CoursReader coursReader, CoursWriter coursWriter) {
        this.coursReader = coursReader;
        this.coursWriter = coursWriter;
    }

    @GetMapping
    public CollectionModel<EntityModel<Episode>> episodes() {
        Collection<Episode> content = this.coursReader.getAllEpisodes().getContent();
        List<EntityModel<Episode>> listeEpisodes = new ArrayList<>();
        content.forEach(episode -> listeEpisodes.add(EntityModel.of(episode)));
        return CollectionModel.of(listeEpisodes);
    }

    @PostMapping
    public void insertEpisode(@RequestBody Episode episode) {
      this.coursWriter.insertEpisode(episode);
    }
}
