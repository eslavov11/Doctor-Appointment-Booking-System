package com.doctorAppointmentBookingSystem.customValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = IsPasswordsMatchingValidator.class)
public @interface IsPasswordsMatching {

    String message() default "Passwords not matching";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
