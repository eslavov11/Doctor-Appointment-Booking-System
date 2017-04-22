package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.WeekScheduleModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.WeekScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Edi on 09-Apr-17.
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    private WeekScheduleService weekScheduleService;

    private DoctorService doctorService;

    @Autowired
    public ScheduleController(WeekScheduleService weekScheduleService, DoctorService doctorService) {
        this.weekScheduleService = weekScheduleService;
        this.doctorService = doctorService;
    }

    @GetMapping("/edit")
    public String getEditSchedule(@ModelAttribute WeekScheduleModel weekScheduleModel, Principal principal) {
        long userId = ((User)((Authentication) principal).getPrincipal()).getId();
        Doctor doctor = this.doctorService.getByUserId(userId);

        this.weekScheduleService.getById(doctor.getWeekSchedule().getId());

        return "schedule/edit";
    }

    @PostMapping("/edit")
    public String editSchedule(@Valid @ModelAttribute WeekScheduleModel weekScheduleModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "schedule/edit";
        }

        //this.weekScheduleService.create(weekScheduleModel);

        return "redirect:/";
    }
}
