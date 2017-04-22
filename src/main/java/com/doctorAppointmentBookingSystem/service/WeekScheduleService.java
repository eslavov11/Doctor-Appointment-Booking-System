package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.entity.WeekSchedule;
import com.doctorAppointmentBookingSystem.model.bindingModel.EditWeekScheduleModel;

/**
 * Created by Edi on 22-Apr-17.
 */
public interface WeekScheduleService {
    EditWeekScheduleModel getById(long id);

    WeekSchedule createDefault();

    void save(EditWeekScheduleModel editWeekScheduleModel);
}
