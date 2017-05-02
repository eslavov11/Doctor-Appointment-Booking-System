package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.PatientRegistrationModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.PatientViewModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Edi on 09-Apr-17.
 */
@Controller
//@RequestMapping("/patient")
public class PatientController {
    private PatientService patientService;

    private DoctorService doctorService;

    @Autowired
    public PatientController(PatientService patientService, DoctorService doctorService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @GetMapping("/register-patient")
    public String getRegister(@ModelAttribute PatientRegistrationModel patientRegistrationModel, Model model) {
        List<DoctorSelectViewModel> doctors = this.doctorService.getAllSelect();
        model.addAttribute("doctors", doctors);

        return "patient-register";
    }

    @PostMapping("/register-patient")
    public String register(@Valid @ModelAttribute PatientRegistrationModel patientRegistrationModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<DoctorSelectViewModel> doctors = this.doctorService.getAllSelect();
            model.addAttribute("doctors", doctors);

            return "patient-register";
        }

        this.patientService.create(patientRegistrationModel);

        return "redirect:/";
    }

    //TODO: admin
    /*@GetMapping("/patients")
    public String getPatients(Model model, @PageableDefault(size = 8) Pageable pageable) {
        Page<PatientViewModel> patients = this.patientService.getAll(pageable);
        model.addAttribute("patients", patients);

        return "patient/patients";
    }*/

    @GetMapping("/doctor/patients")
    public String getDoctorPatients(Model model, @PageableDefault(size = 8) Pageable pageable, Authentication principal) {
        long userId = ((User) principal.getPrincipal()).getId();
        Doctor doctor = this.doctorService.getByUserId(userId);

        Page<PatientViewModel> patients = this.patientService.getPatientsByDoctorId(pageable, doctor.getId());
        model.addAttribute("patients", patients);

        return "patient/patients";
    }
}
