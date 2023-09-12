package com.napas.qr.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PciPasswordValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PciCompliantPassword {
    String message() default "Password phải có 8-49 ký tự, phải có ít nhất 1 ký tự trong nhóm A-Z, a-z, 0-9, ký tự đặc biệt";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
