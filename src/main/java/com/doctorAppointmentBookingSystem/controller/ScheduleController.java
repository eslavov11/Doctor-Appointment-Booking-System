package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.model.bindingModel.WeekScheduleModel;
import com.doctorAppointmentBookingSystem.service.WeekScheduleService;
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
@RequestMapping("/schedule")
public class ScheduleController {
    private WeekScheduleService weekScheduleService;

    @Autowired
    public ScheduleController(WeekScheduleService weekScheduleService) {
        this.weekScheduleService = weekScheduleService;
    }

    @GetMapping("/edit")
    public String getEditSchedule(@ModelAttribute WeekScheduleModel weekScheduleModel) {
        return "schedule/edit";
    }

    @PostMapping("/edit")
    public String editSchedule(@Valid @ModelAttribute WeekScheduleModel weekScheduleModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "schedule/edit";
        }

        this.weekScheduleService.create(weekScheduleModel);

        return "redirect:/";
    }
}
