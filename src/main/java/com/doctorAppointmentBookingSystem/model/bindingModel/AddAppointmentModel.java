package com.doctorAppointmentBookingSystem.model.bindingModel;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.Patient;

import java.util.Date;

/**
 * Created by Edi on 27-Apr-17.
 */
public class AddAppointmentModel {
    private Date date;

    private long appointmentTypeId;

    private String description;

    private Patient patient;

    private Doctor doctor;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getAppointmentTypeId() {
        return appointmentTypeId;
    }

    public void setAppointmentTypeId(long appointmentTypeId) {
        this.appointmentTypeId = appointmentTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
