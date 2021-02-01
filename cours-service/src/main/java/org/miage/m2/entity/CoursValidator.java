package org.miage.m2.entity;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

@Service
public class CoursValidator {

    private Validator validator;

    CoursValidator(Validator validator) {
        this.validator = validator;
    }

    public void validate(CoursInput cours) {
        Set<ConstraintViolation<CoursInput>> violations = validator.validate(cours);
        if (!violations.isEmpty()) {
        throw new ConstraintViolationException(violations);
        }
    }
}