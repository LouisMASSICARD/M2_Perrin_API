package org.miage.intervenantservice.boundary;

import org.miage.intervenantservice.entity.Intervenant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurResource extends JpaRepository<Intervenant, String> {
}
