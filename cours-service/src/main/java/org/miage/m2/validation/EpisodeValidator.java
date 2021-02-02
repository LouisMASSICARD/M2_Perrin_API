package org.miage.m2.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

@Service
public class EpisodeValidator {

    private Validator validator;

    EpisodeValidator(Validator validator) {
        this.validator = validator;
    }

    public void validate(EpisodeInput episode) {
        Set<ConstraintViolation<EpisodeInput>> violations = validator.validate(episode);
        if (!violations.isEmpty()) {
        throw new ConstraintViolationException(violations);
        }
    }
}