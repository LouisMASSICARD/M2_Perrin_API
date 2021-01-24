package org.miage.m2.entity;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

@Service
public class UtilisateurValidator {

  private Validator validator;

  UtilisateurValidator(Validator validator) {
    this.validator = validator;
  }

  public void validate(UtilisateurInput intervenant) {
    Set<ConstraintViolation<UtilisateurInput>> violations = validator.validate(intervenant);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}