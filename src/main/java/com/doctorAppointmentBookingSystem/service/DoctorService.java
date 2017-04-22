package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.model.bindingModel.DoctorRegistrationModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;

import java.util.List;

/**
 * Created by Edi on 16-Apr-17.
 */
public interface DoctorService {
    void create(DoctorRegistrationModel registrationModel);

    Doctor getById(long id);

    Doctor getByUserId(long userId);

    List<DoctorSelectViewModel> getAll();
}
