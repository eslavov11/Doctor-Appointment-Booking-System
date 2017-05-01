package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.bindingModel.ChangePasswordModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.UserRegistrationModel;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by Edi on 06-Apr-17.
 */
public interface UserService extends UserDetailsService {
    User register(UserRegistrationModel registrationModel);

    boolean updatePassword(ChangePasswordModel changePasswordModel);
}
