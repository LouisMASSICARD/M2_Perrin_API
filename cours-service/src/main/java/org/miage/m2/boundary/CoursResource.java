package org.miage.m2.boundary;

import org.miage.m2.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoursResource extends JpaRepository<Utilisateur, String> {
}
