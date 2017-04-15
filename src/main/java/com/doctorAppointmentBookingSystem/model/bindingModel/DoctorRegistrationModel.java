package com.doctorAppointmentBookingSystem.model.bindingModel;

import com.doctorAppointmentBookingSystem.entity.Gender;
import com.doctorAppointmentBookingSystem.entity.SettlePoint;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Edi on 15-Apr-17.
 */
public class DoctorRegistrationModel {
    private String firstName;

    private String lastName;

    private Gender gender;

    private String EGN;

    private String telephoneNumber;

    private Date dateOfBirth;

    private Date startPracticeDate;

    private String settlePoint;

    private String address;

    private String description;

    private Boolean worksWithNZOK;

    private String picturePath;
}
