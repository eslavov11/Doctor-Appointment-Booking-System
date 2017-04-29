package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.entity.Appointment;
import com.doctorAppointmentBookingSystem.entity.AppointmentType;
import com.doctorAppointmentBookingSystem.model.bindingModel.AddAppointmentModel;
import com.doctorAppointmentBookingSystem.repository.AppointmentRepository;
import com.doctorAppointmentBookingSystem.service.AppointmentService;
import com.doctorAppointmentBookingSystem.service.AppointmentTypeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        AppointmentType appointmentType = this.appointmentTypeService.getById(addAppointmentModel.getAppointmentTypeId());
        appointment.setAppointmentType(appointmentType);

        this.appointmentRepository.saveAndFlush(appointment);
    }
}
