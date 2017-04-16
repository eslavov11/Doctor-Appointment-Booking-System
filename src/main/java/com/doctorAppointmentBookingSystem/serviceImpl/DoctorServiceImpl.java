package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.SettlePoint;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.DoctorRegistrationModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.UserRegistrationModel;
import com.doctorAppointmentBookingSystem.repository.DoctorRepository;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.SettlePointService;
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

    private SettlePointService settlePointService;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper,
                             UserService userService, SettlePointService settlePointService) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.settlePointService = settlePointService;
    }

    @Override
    public void create(DoctorRegistrationModel registrationModel) {
        UserRegistrationModel userRegistrationModel = this.modelMapper.map(registrationModel, UserRegistrationModel.class);
        String DEFAULT_DOCTOR_ROLE = "ROLE_DOCTOR";
        userRegistrationModel.setAdditionalRole(DEFAULT_DOCTOR_ROLE);
        User user = this.userService.register(userRegistrationModel);

        SettlePoint settlePoint = this.settlePointService.getById(registrationModel.getSettlePointId());

        Doctor doctor = this.modelMapper.map(registrationModel, Doctor.class);
        doctor.setUser(user);
        doctor.setSettlePoint(settlePoint);

        this.doctorRepository.saveAndFlush(doctor);
    }
}
