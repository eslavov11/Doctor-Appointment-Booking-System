package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.error.Errors;
import com.doctorAppointmentBookingSystem.model.bindingModel.ChangePasswordModel;
import com.doctorAppointmentBookingSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.AuthProvider;
import java.security.Principal;

/**
 * Created by Edi on 09-Apr-17.
 */
@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", Errors.INVALID_CREDENTIALS);
        }

        return "login";
    }

    @GetMapping("/user/change-password")
    public String getRegisterPage(@ModelAttribute ChangePasswordModel changePasswordModel) {
        return "change-password";
    }

    @PostMapping("/user/change-password")
    public String registerUser(@Valid @ModelAttribute ChangePasswordModel changePasswordModel, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "change-password";
        }

        long userId = ((User)((Authentication) principal).getPrincipal()).getId();
        changePasswordModel.setUserId(userId);
        this.userService.updatePassword(changePasswordModel);

        return "redirect:/";
    }
}
