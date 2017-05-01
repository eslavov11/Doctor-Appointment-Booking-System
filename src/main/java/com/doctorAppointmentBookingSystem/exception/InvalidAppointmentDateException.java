package com.doctorAppointmentBookingSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid appointment date.")
public class InvalidAppointmentDateException extends RuntimeException {
}
