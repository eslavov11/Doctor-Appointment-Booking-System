package com.doctorAppointmentBookingSystem.customValidation;

import com.doctorAppointmentBookingSystem.model.bindingModel.UserRegistrationModel;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsPasswordsMatchingValidator implements ConstraintValidator<IsPasswordsMatching, Object> {
    @Override
    public void initialize(IsPasswordsMatching isPasswordsMatching) {
    }

    @Override
    public boolean isValid(Object userClass, ConstraintValidatorContext constraintValidatorContext) {
        return userClass instanceof UserRegistrationModel &&
                ((UserRegistrationModel) userClass).getPassword().equals(((UserRegistrationModel) userClass).getConfirmPassword());
    }
}
