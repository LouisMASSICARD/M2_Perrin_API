package org.miage.m2.boundary;

import org.miage.m2.entity.Intervenant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="intervenants-service")
public interface IntervenantRestClient {

    @GetMapping("/intervenants/{id}")
    Intervenant get(@PathVariable("id") String id);
}
