package org.miage.m2.boundary;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface UtilisateursChannel {
    @Output
    MessageChannel output();

    @Output
    MessageChannel insertCours();

    @Output
    MessageChannel insertEpisode();
}