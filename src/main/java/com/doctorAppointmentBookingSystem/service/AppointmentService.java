package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.model.bindingModel.AddAppointmentModel;
import com.doctorAppointmentBookingSystem.model.viewModel.AppointmentDateViewModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Edi on 17-Apr-17.
 */
public interface AppointmentService {
    void save(AddAppointmentModel addAppointmentModel);

    List<AppointmentDateViewModel> getAllForDate(Date date);
}
