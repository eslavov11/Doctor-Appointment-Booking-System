package com.doctorAppointmentBookingSystem.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsPasswordsMatchingValidator implements ConstraintValidator<IsPasswordsMatching, Object> {
    @Override
    public void initialize(IsPasswordsMatching isPasswordsMatching) {
    }

    @Override
    public boolean isValid(Object bgTelephone, ConstraintValidatorContext constraintValidatorContext) {
        Pattern p = Pattern.compile("^\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})$");
        Matcher m = p.matcher(bgTelephone.toString());

        return m.find();
    }
}
