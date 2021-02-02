package org.miage.m2.boundary;


import java.util.Collections;
import org.miage.m2.entity.Cours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author louis
 *
 */
@Component
public class CoursMessageProcessor {
	private final CoursResource coursResource;
   
	@Autowired
	public CoursMessageProcessor(CoursResource coursResource) {
		this.coursResource = coursResource;
	}
   
	@StreamListener("input")
	public void onMessage(Message<Cours> msg) {
		Cours c = new Cours(msg.getPayload());
		c.setDescription("");
		c.setEpisodesID(Collections.emptySet());
		this.coursResource.save(c);
	}
//   private final CoursRessource coursRessource;
//   
//   @Autowired
//   public CoursMessageProcessor(CoursRessource coursRessource) {
//       this.coursRessource = coursRessource;
//   }
//   
//   @StreamListener("input")
//   public void onMessage(Message<Cours> msg) {
//       Cours c = new Cours(msg.getPayload());
//       c.setDescription("");
//       c.setIntervenantsId(Collections.emptySet());
//       this.coursRessource.save(c);
//   }
}
