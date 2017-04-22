package com.doctorAppointmentBookingSystem.model.bindingModel;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edi on 22-Apr-17.
 */
public class EditWeekScheduleModel {
    private long id;

    private Set<EditDayScheduleModel> editDayScheduleModels;

    public EditWeekScheduleModel() {
        this.setEditDayScheduleModels(new HashSet<>());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<EditDayScheduleModel> getEditDayScheduleModels() {
        return editDayScheduleModels;
    }

    public void setEditDayScheduleModels(Set<EditDayScheduleModel> editDayScheduleModels) {
        this.editDayScheduleModels = editDayScheduleModels;
    }
}
