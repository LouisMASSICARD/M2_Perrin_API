package org.miage.m2.boundary;

import org.miage.m2.entity.Cours;
import org.miage.m2.entity.Episode;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface CoursWriter {
    @Gateway(requestChannel = "insertCours")
    void insertCours(Cours cours);

    @Gateway(requestChannel = "insertEpisode")
    void insertEpisode(Episode episode);
}