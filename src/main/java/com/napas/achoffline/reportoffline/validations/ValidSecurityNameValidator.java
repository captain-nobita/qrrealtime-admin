package com.napas.achoffline.reportoffline.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidSecurityNameValidator implements ConstraintValidator<ValidSecurityName, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() < 30 && value.matches("^[A-Za-z0-9_\s]+$");
    }
}
