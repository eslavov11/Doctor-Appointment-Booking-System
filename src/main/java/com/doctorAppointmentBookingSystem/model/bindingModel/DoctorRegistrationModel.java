package com.doctorAppointmentBookingSystem.model.bindingModel;

import com.doctorAppointmentBookingSystem.customValidation.PasswordConfirmable;
import com.doctorAppointmentBookingSystem.entity.SettlePoint;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Edi on 15-Apr-17.
 */
public class DoctorRegistrationModel implements PasswordConfirmable {
    private String email;

    @Size(min = 5, message = "Username too short")
    private String username;

    @Size(min = 5, message = "Password too short")
    private String password;

    private String confirmPassword;

    private String firstName;

    private String lastName;

    private String EGN;

    private String telephoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startPracticeDate;

    private long settlePoint;

    private String address;

    private String description;

    private Boolean worksWithNZOK;

    private String picturePath;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public Date getStartPracticeDate() {
        return startPracticeDate;
    }

    public void setStartPracticeDate(Date startPracticeDate) {
        this.startPracticeDate = startPracticeDate;
    }

    public long getSettlePoint() {
        return settlePoint;
    }

    public void setSettlePoint(long settlePoint) {
        this.settlePoint = settlePoint;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getWorksWithNZOK() {
        return worksWithNZOK;
    }

    public void setWorksWithNZOK(Boolean worksWithNZOK) {
        this.worksWithNZOK = worksWithNZOK;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
