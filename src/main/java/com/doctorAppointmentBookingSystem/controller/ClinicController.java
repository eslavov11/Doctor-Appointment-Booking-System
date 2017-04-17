package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by Edi on 09-Apr-17.
 */
@Controller
public class ClinicController {
    private ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }
}
