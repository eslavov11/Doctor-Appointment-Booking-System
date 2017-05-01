package com.doctorAppointmentBookingSystem.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsBGTelephoneMatchingValidator implements ConstraintValidator<BGTelephone, Object> {
    @Override
    public void initialize(BGTelephone bgTelephone) {
    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        return userClass instanceof PasswordConfirmable &&
                ((PasswordConfirmable) userClass).getPassword().equals(((PasswordConfirmable) userClass).getConfirmPassword());
    }
}
