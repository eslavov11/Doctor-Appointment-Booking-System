package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.EditWeekScheduleModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.WeekScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/")
    public String getSchedule(Principal principal, Model model) {
/*
        long weekScheduleId = getWeekScheduleId((Authentication) principal);
        EditWeekScheduleModel editWeekScheduleModel = this.weekScheduleService.getById(weekScheduleId);

        model.addAttribute("weekl", editWeekScheduleModel);
*/

        return "schedule/schedule";
    }

    @GetMapping("/edit")
    public String getEditSchedule(Principal principal, Model model) {
        long weekScheduleId = getWeekScheduleId((Authentication) principal);
        EditWeekScheduleModel editWeekScheduleModel = this.weekScheduleService.getById(weekScheduleId);

        model.addAttribute("editWeekScheduleModel", editWeekScheduleModel);

        return "schedule/edit";
    }

    @PostMapping("/edit")
    public String editSchedule(@Valid @ModelAttribute EditWeekScheduleModel editWeekScheduleModel,
                               BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "schedule/edit";
        }

        long weekScheduleId = getWeekScheduleId((Authentication) principal);

        editWeekScheduleModel.setId(weekScheduleId);
        EditWeekScheduleModel editWeekScheduleModelIds = this.weekScheduleService.getById(weekScheduleId);
        for (int i = 0; i < editWeekScheduleModel.getEditDayScheduleModels().size(); i++) {
            editWeekScheduleModel
                    .getEditDayScheduleModels()
                    .get(i).setId(editWeekScheduleModelIds
                            .getEditDayScheduleModels().get(i).getId());
        }

        this.weekScheduleService.save(editWeekScheduleModel);

        return "redirect:/";
    }

    private long getWeekScheduleId(Authentication principal) {
        long userId = ((User) principal.getPrincipal()).getId();
        Doctor doctor = this.doctorService.getByUserId(userId);
        return doctor.getWeekSchedule().getId();
    }
}
