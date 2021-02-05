package org.miage.m2.boundary.resource;

import org.miage.m2.entity.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbonnementResource extends JpaRepository<Abonnement, String> {
}
