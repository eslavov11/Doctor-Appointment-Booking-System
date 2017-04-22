package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.model.bindingModel.DayScheduleModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.EditDayScheduleModel;

/**
 * Created by Edi on 22-Apr-17.
 */
public interface DayScheduleService {
    void create(DayScheduleModel dayScheduleModel);

    void save(EditDayScheduleModel editDayScheduleModel);
    //getById(long id);
}
