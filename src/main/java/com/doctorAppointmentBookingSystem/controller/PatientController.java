package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.model.bindingModel.PatientRegistrationModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Edi on 09-Apr-17.
 */
@Controller
//@RequestMapping("/patient")
public class PatientController {
    private PatientService patientService;

    private DoctorService doctorService;

    @Autowired
    public PatientController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("/register-patient")
    public String getRegisterPage(@ModelAttribute PatientRegistrationModel patientRegistrationModel, Model model) {
        List<DoctorSelectViewModel> doctors = this.doctorService.getAll();

        model.addAttribute("doctors", doctors);

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
