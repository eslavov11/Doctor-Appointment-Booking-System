package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.model.bindingModel.DoctorRegistrationModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.SettlePointViewModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.SettlePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String getRegister(@ModelAttribute DoctorRegistrationModel doctorRegistrationModel, Model model) {
        List<SettlePointViewModel> settlePoints = this.settlePointService.getAll();
        model.addAttribute("settlePoints", settlePoints);

        return "doctor-register";
    }

    @PostMapping("/register-doctor")
    public String register(@Valid @ModelAttribute DoctorRegistrationModel doctorRegistrationModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<SettlePointViewModel> settlePoints = this.settlePointService.getAll();
            model.addAttribute("settlePoints", settlePoints);

            return "doctor-register";
        }

        this.doctorService.create(doctorRegistrationModel);

        return "redirect:/";
    }

    @GetMapping("/doctors")
    public String getBikes(Model model, @PageableDefault(size = 8) Pageable pageable){
        Page<DoctorViewModel> doctors = this.doctorService.getAll(pageable);
        model.addAttribute("doctors", doctors);

        return "doctor/doctors";
    }
}
