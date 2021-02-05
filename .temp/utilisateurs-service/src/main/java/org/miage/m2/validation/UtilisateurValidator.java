package org.miage.m2.validation;

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

  public void validate(UtilisateurInput utilisateur) {
    Set<ConstraintViolation<UtilisateurInput>> violations = validator.validate(utilisateur);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}