package org.miage.m2.boundary;

import org.miage.m2.entity.Cours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="cours", path="cours")
public interface CoursRessource extends CrudRepository<Cours, String> {
}