package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.repository.DoctorSpecialtyRepository;
import com.doctorAppointmentBookingSystem.service.DoctorSpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Edi on 17-Apr-17.
 */
@Service
public class DoctorSpecialtyServiceImpl implements DoctorSpecialtyService {
    private DoctorSpecialtyRepository doctorSpecialtyRepository;

    @Autowired
    public DoctorSpecialtyServiceImpl(DoctorSpecialtyRepository doctorSpecialtyRepository) {
        this.doctorSpecialtyRepository = doctorSpecialtyRepository;
    }
}
