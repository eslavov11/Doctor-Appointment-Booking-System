package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.entity.WeekSchedule;
import com.doctorAppointmentBookingSystem.model.bindingModel.WeekScheduleModel;

/**
 * Created by Edi on 22-Apr-17.
 */
public interface WeekScheduleService {
    WeekScheduleModel getById(long id);

    WeekSchedule createDefault();
}
