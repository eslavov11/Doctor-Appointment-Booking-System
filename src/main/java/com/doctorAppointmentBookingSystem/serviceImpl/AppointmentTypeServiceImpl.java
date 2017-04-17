package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.repository.AppointmentTypeRepository;
import com.doctorAppointmentBookingSystem.service.AppointmentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Edi on 17-Apr-17.
 */
@Service
public class AppointmentTypeServiceImpl implements AppointmentTypeService {
    private AppointmentTypeRepository appointmentTypeRepository;

    @Autowired
    public AppointmentTypeServiceImpl(AppointmentTypeRepository appointmentTypeRepository) {
        this.appointmentTypeRepository = appointmentTypeRepository;
    }
}
