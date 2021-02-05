package org.miage.m2.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatorEnumString implements ConstraintValidator<ValidateEnumString, String> { 
	
	private ValidateEnumString annotation;
	 
    @Override
    public void initialize(ValidateEnumString annotation) {
        this.annotation = annotation;
    }
 
    @Override
    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
        boolean result = false;
         
        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
         
        if(enumValues != null) {
            for(Object enumValue:enumValues) {
                if(valueForValidation.equals(enumValue.toString())) {
                    result = true; 
                    break;
                }
            }
        }
        return result;
    }
} 