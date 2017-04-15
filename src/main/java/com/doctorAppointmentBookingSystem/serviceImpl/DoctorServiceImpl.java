package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.repository.DoctorRepository;
import com.doctorAppointmentBookingSystem.service.DoctorService;
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

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }
}
