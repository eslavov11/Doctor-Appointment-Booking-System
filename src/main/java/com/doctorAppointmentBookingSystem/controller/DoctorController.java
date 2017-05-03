package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.DoctorRegistrationModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.EditDoctorModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.SettlePointViewModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.SettlePointService;
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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/doctor/{id}")
    public String getEditPatient(@PathVariable long id, Model model) {
        DoctorViewModel doctorViewModel = this.doctorService.getViewModelById(id);

        model.addAttribute("doctorViewModel", doctorViewModel);

        return "doctor/doctor";
    }

    @GetMapping("/doctors")
    public String getDoctors(Model model, @PageableDefault(size = 8) Pageable pageable){
        Page<DoctorViewModel> doctors = this.doctorService.getAll(pageable);
        model.addAttribute("doctors", doctors);

        return "doctor/doctors";
    }

    @GetMapping("/register-doctor")
    public String getRegister(@ModelAttribute DoctorRegistrationModel doctorRegistrationModel, Model model) {
        List<SettlePointViewModel> settlePoints = this.settlePointService.getAll();
        model.addAttribute("settlePoints", settlePoints);

        return "doctor/register";
    }

    @PostMapping("/register-doctor")
    public String register(@Valid @ModelAttribute DoctorRegistrationModel doctorRegistrationModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<SettlePointViewModel> settlePoints = this.settlePointService.getAll();
            model.addAttribute("settlePoints", settlePoints);

            return "doctor/register";
        }

        this.doctorService.create(doctorRegistrationModel);

        return "redirect:/";
    }

    @GetMapping("/doctor/edit")
    public String getEditDoctor(Model model, Authentication principal) {
        long userId = ((User) principal.getPrincipal()).getId();
        EditDoctorModel editDoctorModel = this.doctorService.getEditModelByUserId(userId);

        model.addAttribute("editDoctorModel", editDoctorModel);

        return "doctor/edit";
    }

    @PostMapping("/doctor/edit")
    public String editDoctor(@Valid @ModelAttribute EditDoctorModel editDoctorModel, BindingResult bindingResult,
                             Authentication principal) {
        if(bindingResult.hasErrors()){
            return "doctor/edit";
        }

        long userId = ((User) principal.getPrincipal()).getId();
        EditDoctorModel editDoctorModelId = this.doctorService.getEditModelByUserId(userId);
        editDoctorModel.setId(editDoctorModelId.getId());

        this.doctorService.save(editDoctorModel);

        return "redirect:/";
    }
}
