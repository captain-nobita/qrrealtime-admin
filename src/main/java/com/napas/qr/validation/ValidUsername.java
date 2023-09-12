package com.napas.qr.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUsername {
    String message() default "Tên chỉ được phép bao gồm các kí tự a-z 0-9 _";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
