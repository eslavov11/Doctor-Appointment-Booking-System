package com.doctorAppointmentBookingSystem.model.bindingModel;


import com.doctorAppointmentBookingSystem.customValidation.IsPasswordsMatching;
import com.doctorAppointmentBookingSystem.customValidation.PasswordConfirmable;
import jdk.nashorn.internal.ir.annotations.Ignore;

import javax.validation.constraints.Size;

@IsPasswordsMatching
public class UserRegistrationModel implements PasswordConfirmable {
    private String email;

    @Size(min = 5, message = "Username too short")
    private String username;

    @Size(min = 5, message = "Password too short")
    private String password;

    @Size(min = 5, message = "Confirm password too short")
    private String confirmPassword;

    private String additionalRole;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getAdditionalRole() {
        return additionalRole;
    }

    public void setAdditionalRole(String additionalRole) {
        this.additionalRole = additionalRole;
    }
}
