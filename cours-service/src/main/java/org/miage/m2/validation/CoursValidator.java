package org.miage.m2.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.lang3.EnumUtils;
import org.miage.m2.constants.CoursAcces;
import org.miage.m2.constants.CoursStatuts;
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
    
    public boolean validateSatut(String statut) {
       return EnumUtils.isValidEnum(CoursStatuts.class, statut);
    }
    
    public boolean validateAcces(String acces) {
        return EnumUtils.isValidEnum(CoursAcces.class, acces);
     }
}