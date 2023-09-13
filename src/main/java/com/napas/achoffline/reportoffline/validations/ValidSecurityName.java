package com.napas.achoffline.reportoffline.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Check if a string is not null and is a valid security name.
 */
@Documented
@Constraint(validatedBy = ValidSecurityNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSecurityName {
    String message() default "Tên chỉ được phép bao gồm các kí tự A-Z 0-9 _";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
