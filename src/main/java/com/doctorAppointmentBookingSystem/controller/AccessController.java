package com.doctorAppointmentBookingSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Edi on 01-May-17.
 */
@Controller
public class AccessController {
    @GetMapping("/unauthorized")
    public String unauthorized() {
        return "error/403";
    }
}
