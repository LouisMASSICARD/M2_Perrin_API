package org.miage.m2.boundary.queue;

import java.util.Collections;

import org.miage.m2.boundary.resource.CoursRessource;
import org.miage.m2.entity.Cours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class CoursMessageProcessor {
   private final CoursRessource coursRessource;
   
   @Autowired
   public CoursMessageProcessor(CoursRessource coursRessource) {
       this.coursRessource = coursRessource;
   }
   
   @StreamListener("input")
   public void onMessage(Message<Cours> msg) {
       Cours c = new Cours(msg.getPayload());
       c.setDescription("");
    //    c.setIntervenantsId(Collections.emptySet());
       c.setEpisodesID(Collections.emptySet());
       this.coursRessource.save(c);
   }
}
