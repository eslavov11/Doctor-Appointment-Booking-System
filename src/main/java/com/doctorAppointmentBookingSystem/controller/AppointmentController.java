package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.Patient;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.AddAppointmentModel;
import com.doctorAppointmentBookingSystem.model.viewModel.*;
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

    @GetMapping("/patient")
    public String getPatientAppointmentHomePage(Authentication principal, Model model) {
        long userId = ((User)(principal).getPrincipal()).getId();
        Patient patient = this.patientService.getByUserId(userId);
        List<AppointmentViewModel> appointmentViewModels = this.appointmentService.getAllForPatientById(patient.getId());

        model.addAttribute("appointmentViewModels", appointmentViewModels);

        return "appointment/appointments";
    }

    @GetMapping("/doctor")
    public String getDoctorAppointmentHomePage(Authentication principal, Model model) {
        long userId = ((User)(principal).getPrincipal()).getId();
        Doctor doctor = this.doctorService.getByUserId(userId);
        List<AppointmentViewModel> appointmentViewModels = this.appointmentService.getAllForDoctorById(doctor.getId());

        model.addAttribute("appointmentViewModels", appointmentViewModels);

        return "appointment/appointments";
    }

    @GetMapping("/")
    public String getAppointment(@RequestParam("date") @DateTimeFormat(pattern="MM/dd/yyyy hh:mm:ss a") Date date, Model model) {
        AppointmentViewModel appointmentViewModel = this.appointmentService.getByDate(date);

        if (appointmentViewModel == null) {
            //throw TODO:
        }

        model.addAttribute("appointmentViewModel", appointmentViewModel);

        return "appointment/appointment";
    }

    @GetMapping("/patient/add")
    public String getPatientAddAppointment(Principal principal, @RequestParam("date") @DateTimeFormat(pattern="MM/dd/yyyy hh:mm:ss a") Date date,
                                    @ModelAttribute AddAppointmentModel addAppointmentModel, Model model) {
        //TODO: check if date not taken -> go to schedule if tre

        addAppointmentModel.setDate(date);

        long userId = ((User)((Authentication) principal).getPrincipal()).getId();
        Patient patient = this.patientService.getByUserId(userId);
        DoctorSelectViewModel doctorSelectViewModel = this.doctorService.getModelByUserId(patient.getDoctor().getUser().getId());
        model.addAttribute("doctorSelectViewModel", doctorSelectViewModel);

        List<AppointmentTypeViewModel> appointmentTypes = this.appointmentTypeService.getAll();
        model.addAttribute("appointmentTypes", appointmentTypes);

        return "appointment/add";
    }

    @PostMapping("/patient/add")
    public String patientAddAppointment(@RequestParam("date") @DateTimeFormat(pattern="MM/dd/yyyy hh:mm:ss a") Date date,
                                 @Valid @ModelAttribute AddAppointmentModel addAppointmentModel,
                               BindingResult bindingResult, Authentication principal) {
        if (bindingResult.hasErrors()) {
            return "appointment/add";
        }

        //TODO: check if not taken

        addAppointmentModel.setDate(date);

        long userId = ((User)principal.getPrincipal()).getId();
        Patient patient = this.patientService.getByUserId(userId);

        addAppointmentModel.setPatient(patient);
        addAppointmentModel.setDoctor(patient.getDoctor());

        this.appointmentService.save(addAppointmentModel);

        return "redirect:/schedule/";
    }

    @GetMapping("/doctor/add")
    public String getDoctorAddAppointment(Principal principal, @RequestParam("date") @DateTimeFormat(pattern="MM/dd/yyyy hh:mm:ss a") Date date,
                                    @ModelAttribute AddAppointmentModel addAppointmentModel, Model model) {
        //TODO: check if date not taken -> go to schedule if tre

        addAppointmentModel.setDate(date);

        long userId = ((User)((Authentication) principal).getPrincipal()).getId();
        DoctorSelectViewModel doctorSelectViewModel = this.doctorService.getModelByUserId(userId);
        model.addAttribute("doctorSelectViewModel", doctorSelectViewModel);

        List<PatientBasicViewModel> doctorPatients = this.patientService.getPatientsByDoctorId(doctorSelectViewModel.getId());
        model.addAttribute("doctorPatients", doctorPatients);

        List<AppointmentTypeViewModel> appointmentTypes = this.appointmentTypeService.getAll();
        model.addAttribute("appointmentTypes", appointmentTypes);

        return "appointment/add";
    }

    @PostMapping("/doctor/add")
    public String doctorAddAppointment(@RequestParam("date") @DateTimeFormat(pattern="MM/dd/yyyy hh:mm:ss a") Date date,
                                 @Valid @ModelAttribute AddAppointmentModel addAppointmentModel,
                                 BindingResult bindingResult, Authentication principal) {
        if (bindingResult.hasErrors()) {
            return "appointment/add";
        }

        //TODO: check if not taken

        addAppointmentModel.setDate(date);

        long userId = ((User)principal.getPrincipal()).getId();
        Doctor doctor = this.doctorService.getByUserId(userId);

        addAppointmentModel.setDoctor(doctor);

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
