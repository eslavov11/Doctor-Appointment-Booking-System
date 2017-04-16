package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.error.Errors;
import com.doctorAppointmentBookingSystem.model.bindingModel.DoctorRegistrationModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Edi on 09-Apr-17.
 */
@Controller
//@RequestMapping("/doctor")
public class DoctorController {
    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/register-doctor")
    public String getRegisterPage(@ModelAttribute DoctorRegistrationModel registrationModel) {
        return "doctor-register";
    }

    @PostMapping("/register-doctor")
    public String registerUser(@Valid @ModelAttribute DoctorRegistrationModel registrationModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "doctor-register";
        }

        this.doctorService.create(registrationModel);

        return "redirect:/";
    }
}
