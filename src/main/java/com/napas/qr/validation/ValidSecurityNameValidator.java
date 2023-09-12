package com.napas.qr.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidSecurityNameValidator implements ConstraintValidator<ValidSecurityName, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && value.length() < 30 && value.matches("^[A-Z0-9_]+$");
    }
}
