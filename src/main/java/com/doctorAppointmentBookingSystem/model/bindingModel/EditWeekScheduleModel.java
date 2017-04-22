package com.doctorAppointmentBookingSystem.model.bindingModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Edi on 22-Apr-17.
 */
public class EditWeekScheduleModel {
    private long id;

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

    public List<EditDayScheduleModel> getEditDayScheduleModels() {
        return editDayScheduleModels;
    }

    public void setEditDayScheduleModels(List<EditDayScheduleModel> editDayScheduleModels) {
        this.editDayScheduleModels = editDayScheduleModels;
    }
}
