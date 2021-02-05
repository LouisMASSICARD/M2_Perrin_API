package org.miage.m2.boundary;

import org.miage.m2.validation.UtilisateurInput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
//import org.springframework.integration.annotation.Gateway;
//import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("utilisateurs-service")
public interface UtilisateurWriter {
//    @Gateway(requestChannel = "insertUtilisateur")
//    void insertUtilisateur(Utilisateur utilisateur);
  
    @RequestMapping(method = RequestMethod.POST, value = "/utilisateurs")
    ResponseEntity<?> save(UtilisateurInput utilisateur);
}