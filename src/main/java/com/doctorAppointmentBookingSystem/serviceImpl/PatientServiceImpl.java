package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.entity.Patient;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.PatientRegistrationModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.UserRegistrationModel;
import com.doctorAppointmentBookingSystem.repository.PatientRepository;
import com.doctorAppointmentBookingSystem.service.PatientService;
import com.doctorAppointmentBookingSystem.service.UserService;
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

    private UserService userService;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper, UserService userService) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void create(PatientRegistrationModel registrationModel) {
        UserRegistrationModel userRegistrationModel = this.modelMapper.map(registrationModel, UserRegistrationModel.class);
        User user = this.userService.register(userRegistrationModel);

        Patient patient = this.modelMapper.map(registrationModel, Patient.class);
        patient.setUser(user);

        this.patientRepository.save(patient);
    }
}
