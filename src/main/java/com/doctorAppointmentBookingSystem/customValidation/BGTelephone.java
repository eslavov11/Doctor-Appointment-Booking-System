package com.doctorAppointmentBookingSystem.customValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = IsBGTelephoneMatchingValidator.class)
public @interface BGTelephone {

    String message() default "Invalid telephone number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
