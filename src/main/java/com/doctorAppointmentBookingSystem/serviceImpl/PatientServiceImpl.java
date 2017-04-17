package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.Patient;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.PatientRegistrationModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.UserRegistrationModel;
import com.doctorAppointmentBookingSystem.repository.PatientRepository;
import com.doctorAppointmentBookingSystem.service.DoctorService;
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

    private DoctorService doctorService;

    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper,
                              UserService userService, DoctorService doctorService) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.doctorService = doctorService;
    }

    @Override
    public void create(PatientRegistrationModel registrationModel) {
        UserRegistrationModel userRegistrationModel = this.modelMapper.map(registrationModel, UserRegistrationModel.class);
        String DEFAULT_PATIENT_ROLE = "ROLE_PATIENT";
        userRegistrationModel.setAdditionalRole(DEFAULT_PATIENT_ROLE);
        User user = this.userService.register(userRegistrationModel);

        Doctor doctor = this.doctorService.getById(registrationModel.getDoctorId());

        Patient patient = this.modelMapper.map(registrationModel, Patient.class);
        patient.setUser(user);
        patient.setDoctor(doctor);

        this.patientRepository.saveAndFlush(patient);
    }
}
