package com.doctorAppointmentBookingSystem.model.bindingModel;

/**
 * Created by Edi on 22-Apr-17.
 */
public class EditDayScheduleModel {
    private long id;

    private String startTimeStr;

    private String endTimeStr;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStartTimeStr() {
        return startTimeStr;
    }

    public void setStartTimeStr(String startTimeStr) {
        this.startTimeStr = startTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }
}
