package org.miage.m2.boundary;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.miage.m2.entity.Intervenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IntervenantClient {
    @Autowired
    IntervenantRestClient restClient;
    
    @HystrixCommand(fallbackMethod = "fallback")
    public Intervenant get(String id) {
        return restClient.get(id);
    }

    public Intervenant fallback(String id) {
        return new Intervenant("non disponible");
    }
    
}

