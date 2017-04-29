package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.entity.Patient;
import com.doctorAppointmentBookingSystem.model.bindingModel.PatientRegistrationModel;

/**
 * Created by Edi on 16-Apr-17.
 */
public interface PatientService {
    void create(PatientRegistrationModel registrationModel);

    Patient getByUserId(long userId);
}
