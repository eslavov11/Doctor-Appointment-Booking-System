package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.Patient;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.AddAppointmentModel;
import com.doctorAppointmentBookingSystem.model.viewModel.AppointmentDateViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.AppointmentTypeViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;
import com.doctorAppointmentBookingSystem.service.AppointmentService;
import com.doctorAppointmentBookingSystem.service.AppointmentTypeService;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    private PatientService patientService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, AppointmentTypeService appointmentTypeService,
                                 DoctorService doctorService, PatientService patientService) {
        this.appointmentService = appointmentService;
        this.appointmentTypeService = appointmentTypeService;
        this.doctorService = doctorService;
        this.patientService = patientService;
    }

    @GetMapping("/add")
    public String getAddAppointment(Principal principal, @RequestParam("date") @DateTimeFormat(pattern="MM/dd/yyyy hh:mm:ss a") Date date,
                                    @ModelAttribute AddAppointmentModel addAppointmentModel, Model model) {
        addAppointmentModel.setDate(date);

        long userId = ((User)((Authentication) principal).getPrincipal()).getId();
        Patient patient = this.patientService.getByUserId(userId);
        DoctorSelectViewModel doctorSelectViewModel = this.doctorService.getModelByUserId(patient.getDoctor().getUser().getId());
        model.addAttribute("doctorSelectViewModel", doctorSelectViewModel);

        List<AppointmentTypeViewModel> appointmentTypes = this.appointmentTypeService.getAll();
        model.addAttribute("appointmentTypes", appointmentTypes);

        return "appointment/add";
    }

    @PostMapping("/add")
    public String addAppointment(@RequestParam("date") @DateTimeFormat(pattern="MM/dd/yyyy hh:mm:ss a") Date date,
                                 @Valid @ModelAttribute AddAppointmentModel addAppointmentModel,
                               BindingResult bindingResult, Authentication principal) {
        if (bindingResult.hasErrors()) {
            return "appointment/add";
        }

        addAppointmentModel.setDate(date);

        long userId = ((User)principal.getPrincipal()).getId();
        Patient patient = this.patientService.getByUserId(userId);

        addAppointmentModel.setPatient(patient);
        addAppointmentModel.setDoctor(patient.getDoctor());

        this.appointmentService.save(addAppointmentModel);

        return "redirect:/schedule/";
    }

    @GetMapping("/getForDate")
    public ResponseEntity<List<AppointmentDateViewModel>> getWeekSchedule(
            @RequestParam("date") @DateTimeFormat(pattern="MM/dd/yyyy") Date date) {
        List<AppointmentDateViewModel> appointmentDateViewModels = this.appointmentService.getAllForDate(date);

        return ResponseEntity.ok(appointmentDateViewModels);
    }
}
