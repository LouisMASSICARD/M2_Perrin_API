package org.miage.m2.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

@Service
public class AbonnementValidator {

  private Validator validator;

  AbonnementValidator(Validator validator) {
    this.validator = validator;
  }

  public void validate(AbonnementInput abonnement) {
    Set<ConstraintViolation<AbonnementInput>> violations = validator.validate(abonnement);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}