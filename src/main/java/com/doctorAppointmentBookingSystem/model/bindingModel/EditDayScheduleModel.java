package com.doctorAppointmentBookingSystem.model.bindingModel;

import java.sql.Time;

/**
 * Created by Edi on 22-Apr-17.
 */
public class EditDayScheduleModel {
    private long id;

    private String dayOfWeek;

    private Time startTime;

    private Time endTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }
}
