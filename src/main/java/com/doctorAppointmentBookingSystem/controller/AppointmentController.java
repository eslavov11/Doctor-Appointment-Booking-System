package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.AddAppointmentModel;
import com.doctorAppointmentBookingSystem.model.viewModel.AppointmentTypeViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;
import com.doctorAppointmentBookingSystem.service.AppointmentService;
import com.doctorAppointmentBookingSystem.service.AppointmentTypeService;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * Created by Edi on 09-Apr-17.
 */
@Controller
@RequestMapping("/appointment")
public class AppointmentController {
    private AppointmentService appointmentService;

    private AppointmentTypeService appointmentTypeService;

    private DoctorService doctorService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, AppointmentTypeService appointmentTypeService,
                                 DoctorService doctorService) {
        this.appointmentService = appointmentService;
        this.appointmentTypeService = appointmentTypeService;
        this.doctorService = doctorService;
    }

    @GetMapping("/add")
    public String getAddAppointment(Principal principal, @RequestParam("date") @DateTimeFormat(pattern="MM/dd/yyyy hh:mm:ss") Date date,
                                    @ModelAttribute AddAppointmentModel addAppointmentModel, Model model) {
        addAppointmentModel.setDate(date);

        long userId = ((User)((Authentication) principal).getPrincipal()).getId();
        DoctorSelectViewModel doctorSelectViewModel = this.doctorService.getModelByUserId(userId);
        model.addAttribute("doctorSelectViewModel", doctorSelectViewModel);

        List<AppointmentTypeViewModel> appointmentTypes = this.appointmentTypeService.getAll();
        model.addAttribute("appointmentTypes", appointmentTypes);

        return "appointment/add";
    }

    @PostMapping("/edit")
    public String editSchedule(@Valid @ModelAttribute AddAppointmentModel addAppointmentModel,
                               BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "appointment/add";
        }

        return "redirect:/";
    }
}
