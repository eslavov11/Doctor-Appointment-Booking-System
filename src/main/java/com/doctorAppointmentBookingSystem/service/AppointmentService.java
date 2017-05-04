package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.model.bindingModel.AddAppointmentModel;
import com.doctorAppointmentBookingSystem.model.viewModel.AppointmentDateViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.AppointmentViewModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

/**
 * Created by Edi on 17-Apr-17.
 */
public interface AppointmentService {
    void save(AddAppointmentModel addAppointmentModel);

    List<AppointmentDateViewModel> getAllForDateAndDoctor(Date date, long doctorId);

    List<AppointmentViewModel> getAllForPatientById(long patientId);

    Page<AppointmentViewModel> getAllForPatientById(long patientId, Pageable pageable);

    List<AppointmentViewModel> getAllForDoctorById(long doctorId);

    Page<AppointmentViewModel> getAllForDoctorById(long doctorId, Pageable pageable);

    AppointmentViewModel getByDateAndDoctorId(Date date, long doctorId);

    AppointmentViewModel getById(long id);
}
