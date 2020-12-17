package com.tui.proof.ws.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = FlightAvailabilityValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Available {

    String message() default "{error.flight.not_available}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
