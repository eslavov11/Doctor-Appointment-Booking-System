package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.EditPatientModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.PatientRegistrationModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.LogViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.PatientViewModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.LogService;
import com.doctorAppointmentBookingSystem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Edi on 09-Apr-17.
 */
@Controller
@RequestMapping("/log")
public class LogController {
    private LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("")
    public String getLog(Model model, @PageableDefault(size = 8) Pageable pageable) {
        Page<LogViewModel> log = this.logService.getAll(pageable);
        model.addAttribute("log", log);

        return "admin/log";
    }
}
