package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.DoctorRegistrationModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.UserRegistrationModel;
import com.doctorAppointmentBookingSystem.repository.DoctorRepository;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Edi on 16-Apr-17.
 */
@Service
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;

    private ModelMapper modelMapper;

    private UserService userService;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper, UserService userService) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void create(DoctorRegistrationModel registrationModel) {
        UserRegistrationModel userRegistrationModel = this.modelMapper.map(registrationModel, UserRegistrationModel.class);
        User user = this.userService.register(userRegistrationModel);

        Doctor doctor = this.modelMapper.map(registrationModel, Doctor.class);
        doctor.setUser(user);

        this.doctorRepository.save(doctor);
    }
}
