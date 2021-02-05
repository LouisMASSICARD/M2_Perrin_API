package org.miage.m2.boundary.resource;

import org.miage.m2.entity.Cours;
// import org.springframework.data.repository.CrudRepository;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.JpaRepository;

// @RepositoryRestResource(collectionResourceRel="cours", path="cours")
// public interface CoursResource extends CrudRepository<Cours, String> {
public interface CoursResource extends JpaRepository<Cours, String> {

	// Recherche des cours par statut : actif, supprime, ...
	Iterable<Cours> findByStatut(String statut);

	// Recherche des cours par access : gratuit, payant, ...
	Iterable<Cours> findByAcces(String acces);
}
