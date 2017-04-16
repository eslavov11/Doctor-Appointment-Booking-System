package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.model.bindingModel.PatientRegistrationModel;
import com.doctorAppointmentBookingSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by Edi on 09-Apr-17.
 */
@Controller
//@RequestMapping("/patient")
public class PatientController {
    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/register-patient")
    public String getRegisterPage(@ModelAttribute PatientRegistrationModel patientRegistrationModel) {
        return "patient-register";
    }

    @PostMapping("/register-patient")
    public String registerUser(@Valid @ModelAttribute PatientRegistrationModel patientRegistrationModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "patient-register";
        }

        this.patientService.create(patientRegistrationModel);

        return "redirect:/";
    }
}
