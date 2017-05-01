package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.entity.Appointment;
import com.doctorAppointmentBookingSystem.entity.AppointmentType;
import com.doctorAppointmentBookingSystem.model.bindingModel.AddAppointmentModel;
import com.doctorAppointmentBookingSystem.model.viewModel.AppointmentDateViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.AppointmentViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.PatientBasicViewModel;
import com.doctorAppointmentBookingSystem.repository.AppointmentRepository;
import com.doctorAppointmentBookingSystem.service.AppointmentService;
import com.doctorAppointmentBookingSystem.service.AppointmentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Edi on 17-Apr-17.
 */
@Service
public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;

    private ModelMapper modelMapper;

    private AppointmentTypeService appointmentTypeService;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, ModelMapper modelMapper,
                                  AppointmentTypeService appointmentTypeService) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = modelMapper;
        this.appointmentTypeService = appointmentTypeService;
    }

    @Override
    public void save(AddAppointmentModel addAppointmentModel) {
        Appointment appointment = this.modelMapper.map(addAppointmentModel, Appointment.class);

        AppointmentType appointmentType = this.appointmentTypeService.getById(addAppointmentModel.getType());
        appointment.setAppointmentType(appointmentType);

        this.appointmentRepository.saveAndFlush(appointment);
    }

    @Override
    public List<AppointmentDateViewModel> getAllForDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        Date endDate = cal.getTime();

        List<Appointment> appointments = this.appointmentRepository.findAllBetweenDates(date, endDate);

        List<AppointmentDateViewModel> appointmentDateViewModels = new ArrayList<>();

        for (Appointment appointment : appointments) {
            AppointmentDateViewModel appointmentDateViewModel = this.modelMapper.map(appointment, AppointmentDateViewModel.class);
            appointmentDateViewModels.add(appointmentDateViewModel);
        }

        return appointmentDateViewModels;
    }

    @Override
    public AppointmentViewModel getByDate(Date date) {
        Appointment appointment = this.appointmentRepository.findOneByDate(date);

        AppointmentViewModel appointmentViewModel = this.modelMapper.map(appointment, AppointmentViewModel.class);
        PatientBasicViewModel patientBasicViewModel = this.modelMapper.map(appointment.getPatient(), PatientBasicViewModel.class);
        appointmentViewModel.setPatientBasicViewModel(patientBasicViewModel);
        DoctorSelectViewModel doctorSelectViewModel = this.modelMapper.map(appointment.getDoctor(), DoctorSelectViewModel.class);
        appointmentViewModel.setDoctorSelectViewModel(doctorSelectViewModel);

        appointmentViewModel.setType(appointment.getAppointmentType().getName());

        return appointmentViewModel;
    }
}
