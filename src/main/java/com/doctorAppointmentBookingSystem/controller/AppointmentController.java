package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.Patient;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.exception.AppointmentNotFoundException;
import com.doctorAppointmentBookingSystem.exception.InvalidAppointmentDateException;
import com.doctorAppointmentBookingSystem.model.bindingModel.AddAppointmentModel;
import com.doctorAppointmentBookingSystem.model.viewModel.*;
import com.doctorAppointmentBookingSystem.service.AppointmentService;
import com.doctorAppointmentBookingSystem.service.AppointmentTypeService;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public String getPatientAppointmentHomePage(Authentication principal, Model model, @PageableDefault(size = 8) Pageable pageable) {
        long userId = ((User) (principal).getPrincipal()).getId();
        Patient patient = this.patientService.getByUserId(userId);
        Page<AppointmentViewModel> appointmentViewModels = this.appointmentService.getAllForPatientById(patient.getId(), pageable);

        model.addAttribute("appointmentViewModels", appointmentViewModels);

        return "appointment/appointments";
    }

    @GetMapping("/doctor")
    public String getDoctorAppointmentHomePage(Authentication principal, Model model, @PageableDefault(size = 8) Pageable pageable) {
        long userId = ((User) (principal).getPrincipal()).getId();
        Doctor doctor = this.doctorService.getByUserId(userId);
        Page<AppointmentViewModel> appointmentViewModels = this.appointmentService.getAllForDoctorById(doctor.getId(), pageable);

        model.addAttribute("appointmentViewModels", appointmentViewModels);

        return "appointment/appointments";
    }

    @GetMapping("/doctor/{appointmentId}")
    public String getDoctorAppointment(@PathVariable long appointmentId, Authentication principal, Model model) {
        long userId = ((User) (principal).getPrincipal()).getId();
        Doctor doctor = this.doctorService.getByUserId(userId);
        AppointmentViewModel appointmentViewModel = this.appointmentService.getById(appointmentId);

        // Is this appointment to this doctor
        if (appointmentViewModel.getDoctorSelectViewModel().getId() != doctor.getId()) {
            return "redirect:/appointment/doctor";
        }

        model.addAttribute("appointmentViewModel", appointmentViewModel);

        return "appointment/appointment";
    }

    @GetMapping("/patient/{appointmentId}")
    public String getPatientAppointment(@PathVariable long appointmentId, Authentication principal, Model model) {
        long userId = ((User) (principal).getPrincipal()).getId();
        Patient patient = this.patientService.getByUserId(userId);
        AppointmentViewModel appointmentViewModel = this.appointmentService.getById(appointmentId);

        // Is this appointment to this patient
        if (appointmentViewModel.getPatientBasicViewModel().getId() != patient.getId()) {
            return "redirect:/appointment/patient";
        }

        model.addAttribute("appointmentViewModel", appointmentViewModel);

        return "appointment/appointment";
    }

    @GetMapping("/")
    public String getAppointment(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date, Model model,
                                 Authentication principal, HttpServletRequest request) {
        long doctorId = getDoctorId(principal, request);
        AppointmentViewModel appointmentViewModel = this.appointmentService.getByDateAndDoctorId(date, doctorId);

        model.addAttribute("appointmentViewModel", appointmentViewModel);

        return "appointment/appointment";
    }

    @GetMapping("/patient/add")
    public String getPatientAddAppointment(Principal principal, @RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
                                           @ModelAttribute AddAppointmentModel addAppointmentModel, Model model) {
        if (date.before(new Date())) {
            throw new InvalidAppointmentDateException();
        }

        addAppointmentModel.setDate(date);

        long userId = ((User) ((Authentication) principal).getPrincipal()).getId();
        Patient patient = this.patientService.getByUserId(userId);
        DoctorSelectViewModel doctorSelectViewModel = this.doctorService.getModelByUserId(patient.getDoctor().getUser().getId());
        model.addAttribute("doctorSelectViewModel", doctorSelectViewModel);

        List<AppointmentTypeViewModel> appointmentTypes = this.appointmentTypeService.getAll();
        model.addAttribute("appointmentTypes", appointmentTypes);

        return "appointment/add";
    }

    @PostMapping("/patient/add")
    public String patientAddAppointment(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
                                        @Valid @ModelAttribute AddAppointmentModel addAppointmentModel,
                                        BindingResult bindingResult, Authentication principal, Model model) {
        if (date.before(new Date())) {
            throw new InvalidAppointmentDateException();
        }

        if (bindingResult.hasErrors()) {
            addAppointmentModel.setDate(date);

            long userId = ((User) (principal).getPrincipal()).getId();
            Patient patient = this.patientService.getByUserId(userId);
            DoctorSelectViewModel doctorSelectViewModel = this.doctorService.getModelByUserId(patient.getDoctor().getUser().getId());
            model.addAttribute("doctorSelectViewModel", doctorSelectViewModel);

            List<AppointmentTypeViewModel> appointmentTypes = this.appointmentTypeService.getAll();
            model.addAttribute("appointmentTypes", appointmentTypes);

            return "appointment/add";
        }

        addAppointmentModel.setDate(date);

        long userId = ((User) principal.getPrincipal()).getId();
        Patient patient = this.patientService.getByUserId(userId);

        addAppointmentModel.setPatient(patient);
        addAppointmentModel.setDoctor(patient.getDoctor());

        this.appointmentService.save(addAppointmentModel);

        return "redirect:/schedule/";
    }

    @GetMapping("/doctor/add")
    public String getDoctorAddAppointment(Principal principal,
                                          @RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
                                          @ModelAttribute AddAppointmentModel addAppointmentModel, Model model) {
        if (date.before(new Date())) {
            throw new InvalidAppointmentDateException();
        }

        addAppointmentModel.setDate(date);

        long userId = ((User) ((Authentication) principal).getPrincipal()).getId();
        DoctorSelectViewModel doctorSelectViewModel = this.doctorService.getModelByUserId(userId);
        model.addAttribute("doctorSelectViewModel", doctorSelectViewModel);

        List<PatientBasicViewModel> doctorPatients = this.patientService.getPatientsByDoctorId(doctorSelectViewModel.getId());
        model.addAttribute("doctorPatients", doctorPatients);

        List<AppointmentTypeViewModel> appointmentTypes = this.appointmentTypeService.getAll();
        model.addAttribute("appointmentTypes", appointmentTypes);

        return "appointment/add";
    }

    @PostMapping("/doctor/add")
    public String doctorAddAppointment(@RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy hh:mm:ss a") Date date,
                                       @Valid @ModelAttribute AddAppointmentModel addAppointmentModel,
                                       BindingResult bindingResult, Authentication principal, Model model) {
        if (date.before(new Date())) {
            throw new InvalidAppointmentDateException();
        }

        if (bindingResult.hasErrors()) {
            addAppointmentModel.setDate(date);

            long userId = ((User)principal.getPrincipal()).getId();
            DoctorSelectViewModel doctorSelectViewModel = this.doctorService.getModelByUserId(userId);
            model.addAttribute("doctorSelectViewModel", doctorSelectViewModel);

            List<PatientBasicViewModel> doctorPatients = this.patientService.getPatientsByDoctorId(doctorSelectViewModel.getId());
            model.addAttribute("doctorPatients", doctorPatients);

            List<AppointmentTypeViewModel> appointmentTypes = this.appointmentTypeService.getAll();
            model.addAttribute("appointmentTypes", appointmentTypes);

            return "appointment/add";
        }


        addAppointmentModel.setDate(date);

        long userId = ((User) principal.getPrincipal()).getId();
        Doctor doctor = this.doctorService.getByUserId(userId);

        addAppointmentModel.setDoctor(doctor);

        this.appointmentService.save(addAppointmentModel);

        return "redirect:/schedule/";
    }

    @GetMapping("/getForDate")
    public ResponseEntity<List<AppointmentDateViewModel>> getWeekSchedule(
            @RequestParam("date") @DateTimeFormat(pattern = "MM/dd/yyyy") Date date, Authentication principal, HttpServletRequest request) {
        long doctorId = getDoctorId(principal, request);

        List<AppointmentDateViewModel> appointmentDateViewModels = this.appointmentService.getAllForDateAndDoctor(date, doctorId);

        return ResponseEntity.ok(appointmentDateViewModels);
    }

    private long getDoctorId(Authentication principal, HttpServletRequest request) {
        if (principal != null) {
            long userId = ((User) principal.getPrincipal()).getId();
            if (request.isUserInRole("ROLE_DOCTOR")) {
                return this.doctorService.getByUserId(userId).getId();
            } else if (request.isUserInRole("ROLE_PATIENT")) {
                return this.patientService.getByUserId(userId).getDoctor().getId();
            }
        }

        return 0;
    }

    @ExceptionHandler(AppointmentNotFoundException.class)
    public String catchAppointmentNotFoundException() {
        return "error/appointment-not-found";
    }

    @ExceptionHandler(InvalidAppointmentDateException .class)
    public String catchInvalidAppointment() {
        return "error/invalid-appointment-date";
    }
}
