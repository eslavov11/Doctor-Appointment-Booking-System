package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.entity.AppointmentType;
import com.doctorAppointmentBookingSystem.model.viewModel.AppointmentTypeViewModel;

import java.util.List;

/**
 * Created by Edi on 17-Apr-17.
 */
public interface AppointmentTypeService {
    List<AppointmentTypeViewModel> getAll();

    AppointmentType getById(long id);
}
