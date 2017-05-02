package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.entity.Patient;
import com.doctorAppointmentBookingSystem.model.bindingModel.PatientRegistrationModel;
import com.doctorAppointmentBookingSystem.model.viewModel.PatientBasicViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.PatientViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Edi on 16-Apr-17.
 */
public interface PatientService {
    void create(PatientRegistrationModel registrationModel);

    Patient getByUserId(long userId);

    PatientBasicViewModel getBasicById(long id);

    List<PatientBasicViewModel> getPatientsByDoctorId(long doctorId);

    Page<PatientViewModel> getPatientsByDoctorId(Pageable pageable, long doctorId);

    Page<PatientViewModel> getAll(Pageable pageable);
}
