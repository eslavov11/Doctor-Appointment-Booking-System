package com.doctorAppointmentBookingSystem.model.bindingModel;

import com.doctorAppointmentBookingSystem.customValidation.BGTelephone;
import com.doctorAppointmentBookingSystem.customValidation.IsPasswordsMatching;
import com.doctorAppointmentBookingSystem.customValidation.PasswordConfirmable;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Edi on 15-Apr-17.
 */
@IsPasswordsMatching
public class PatientRegistrationModel implements PasswordConfirmable {

    @NotBlank(message = "Invalid email address")
    @Email(message = "Invalid email address")
    private String email;

    @Size(min = 5, message = "Username too short")
    private String username;

    @Size(min = 5, message = "Password too short")
    private String password;

    @NotBlank
    private String confirmPassword;

    @Size(min = 5, message = "First name too short")
    private String firstName;

    @Size(min = 5, message = "Last name too short")
    private String lastName;

    @Size(min = 10, max=10, message = "Invalid EGN")
    private String EGN;

    @BGTelephone
    private String telephoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Invalid date of birth")
    private Date dateOfBirth;

    private Date dateOfEnrollment;

    @NotBlank(message = "Invalid gender.")
    @Pattern(regexp = "^(MALE|FEMALE)$", message = "Invalid gender.")
    private String gender;

    private Boolean isInsured;

    private long doctor;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEGN() {
        return EGN;
    }

    public void setEGN(String EGN) {
        this.EGN = EGN;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(Date dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getIsInsured() {
        return isInsured;
    }

    public void setInsured(Boolean insured) {
        isInsured = insured;
    }

    public long getDoctor() {
        return doctor;
    }

    public void setDoctor(long doctor) {
        this.doctor = doctor;
    }
}
