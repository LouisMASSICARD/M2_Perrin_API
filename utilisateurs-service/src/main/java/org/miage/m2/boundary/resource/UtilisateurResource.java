package org.miage.m2.boundary.resource;

import org.miage.m2.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurResource extends JpaRepository<Utilisateur, String> {
}
