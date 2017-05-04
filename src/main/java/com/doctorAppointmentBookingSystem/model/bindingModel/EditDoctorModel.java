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
 * Created by Edi on 03-May-17.
 */
public class EditDoctorModel {
    private long id;

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

    @NotBlank(message = "Invalid gender.")
    @Pattern(regexp = "^(MALE|FEMALE)$", message = "Invalid gender.")
    private String gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "PST")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Invalid start practice date")
    private Date startPracticeDate;

    private long settlePointId;

    @Size(max = 256, message = "Invalid address length")
    private String address;

    @Size(max = 256, message = "Invalid description length")
    private String description;

    private Boolean worksWithNZOK;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getSettlePointId() {
        return settlePointId;
    }

    public void setSettlePointId(long settlePointId) {
        this.settlePointId = settlePointId;
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
}
