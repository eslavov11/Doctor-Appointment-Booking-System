package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.SettlePoint;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.entity.WeekSchedule;
import com.doctorAppointmentBookingSystem.model.bindingModel.DoctorRegistrationModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.UserRegistrationModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;
import com.doctorAppointmentBookingSystem.repository.DoctorRepository;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.SettlePointService;
import com.doctorAppointmentBookingSystem.service.UserService;
import com.doctorAppointmentBookingSystem.service.WeekScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edi on 16-Apr-17.
 */
@Service
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;

    private ModelMapper modelMapper;

    private UserService userService;

    private SettlePointService settlePointService;

    private WeekScheduleService weekScheduleService;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper,
                             UserService userService, SettlePointService settlePointService,
                             WeekScheduleService weekScheduleService) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.settlePointService = settlePointService;
        this.weekScheduleService = weekScheduleService;
    }

    @Override
    public void create(DoctorRegistrationModel registrationModel) {
        User user = this.createDoctorUser(registrationModel);
        SettlePoint settlePoint = this.settlePointService.getById(registrationModel.getSettlePointId());
        WeekSchedule weekSchedule = this.weekScheduleService.createDefault();

        Doctor doctor = this.modelMapper.map(registrationModel, Doctor.class);
        doctor.setUser(user);
        doctor.setSettlePoint(settlePoint);
        doctor.setWeekSchedule(weekSchedule);

        this.doctorRepository.saveAndFlush(doctor);
    }

    @Override
    public Doctor getById(long id) {
        return this.doctorRepository.getOne(id);
    }

    @Override
    public List<DoctorSelectViewModel> getAll() {
        List<Doctor> doctors = this.doctorRepository.findAllByOrderByFirstNameAscLastName();
        List<DoctorSelectViewModel> doctorSelectViewModels = new ArrayList<>();
        for (Doctor doctor : doctors) {
            DoctorSelectViewModel doctorSelectViewModel = this.modelMapper.map(doctor, DoctorSelectViewModel.class);
            doctorSelectViewModels.add(doctorSelectViewModel);
        }

        return doctorSelectViewModels;
    }

    @Override
    public Doctor getByUserId(long userId) {
        return this.doctorRepository.findOneByUserId(userId);
    }

    private User createDoctorUser(DoctorRegistrationModel registrationModel) {
        UserRegistrationModel userRegistrationModel = this.modelMapper.map(registrationModel, UserRegistrationModel.class);
        String DEFAULT_DOCTOR_ROLE = "ROLE_DOCTOR";
        userRegistrationModel.setAdditionalRole(DEFAULT_DOCTOR_ROLE);
        return this.userService.register(userRegistrationModel);
    }
}
