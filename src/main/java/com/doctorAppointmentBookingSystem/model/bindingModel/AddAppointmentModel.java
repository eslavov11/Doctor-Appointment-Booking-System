package com.doctorAppointmentBookingSystem.model.bindingModel;

import java.util.Date;

/**
 * Created by Edi on 27-Apr-17.
 */
public class AddAppointmentModel {
    private Date date;

    private long appointmentTypeId;

    private String description;

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
}
