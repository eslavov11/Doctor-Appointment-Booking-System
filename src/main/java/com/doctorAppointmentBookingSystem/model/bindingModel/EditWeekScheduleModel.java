package com.doctorAppointmentBookingSystem.model.bindingModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edi on 22-Apr-17.
 */
public class EditWeekScheduleModel {
    private long id;

    private int appointmentDuration;

    private List<EditDayScheduleModel> editDayScheduleModels;

    public EditWeekScheduleModel() {
        this.setEditDayScheduleModels(new ArrayList<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAppointmentDuration() {
        return appointmentDuration;
    }

    public void setAppointmentDuration(int appointmentDuration) {
        this.appointmentDuration = appointmentDuration;
    }

    public List<EditDayScheduleModel> getEditDayScheduleModels() {
        return editDayScheduleModels;
    }

    public void setEditDayScheduleModels(List<EditDayScheduleModel> editDayScheduleModels) {
        this.editDayScheduleModels = editDayScheduleModels;
    }
}
