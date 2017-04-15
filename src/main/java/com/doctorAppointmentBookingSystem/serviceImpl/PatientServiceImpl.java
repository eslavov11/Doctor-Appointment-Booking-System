package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.repository.PatientRepository;
import com.doctorAppointmentBookingSystem.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Edi on 16-Apr-17.
 */
@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;

    private ModelMapper modelMapper;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }
}
