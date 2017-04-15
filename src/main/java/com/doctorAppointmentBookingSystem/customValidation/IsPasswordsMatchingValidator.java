package com.doctorAppointmentBookingSystem.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordsMatchingValidator implements ConstraintValidator<IsPasswordsMatching, Object> {
    @Override
    public void initialize(IsPasswordsMatching isPasswordsMatching) {
    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        return userClass instanceof PasswordConfirmable &&
                ((PasswordConfirmable) userClass).getPassword().equals(((PasswordConfirmable) userClass).getConfirmPassword());
    }
}
