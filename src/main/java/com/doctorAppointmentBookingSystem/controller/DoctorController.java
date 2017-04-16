package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.SettlePoint;
import com.doctorAppointmentBookingSystem.model.bindingModel.DoctorRegistrationModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.SettlePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Edi on 09-Apr-17.
 */
@Controller
//@RequestMapping("/doctor")
public class DoctorController {
    private DoctorService doctorService;
    private SettlePointService settlePointService;

    @Autowired
    public DoctorController(DoctorService doctorService, SettlePointService settlePointService) {
        this.doctorService = doctorService;
        this.settlePointService = settlePointService;
    }

    @GetMapping("/register-doctor")
    public String getRegisterPage(@ModelAttribute DoctorRegistrationModel doctorRegistrationModel, Model model) {
        List<SettlePoint> settlePoints = this.settlePointService.getAll();

        model.addAttribute("settlePoints", settlePoints);

        return "doctor-register";
    }

    @PostMapping("/register-doctor")
    public String registerUser(@Valid @ModelAttribute DoctorRegistrationModel doctorRegistrationModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "doctor-register";
        }

        this.doctorService.create(doctorRegistrationModel);

        return "redirect:/";
    }
}
