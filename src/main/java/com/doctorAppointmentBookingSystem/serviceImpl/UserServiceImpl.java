package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.model.bindingModel.UserRegistrationModel;
import com.doctorAppointmentBookingSystem.repository.UserRepository;
import com.doctorAppointmentBookingSystem.service.RoleService;
import com.doctorAppointmentBookingSystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Edi on 06-Apr-17.
 */
@Service
public class UserServiceImpl implements UserService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserRepository userRepository;

    private RoleService roleService;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserRepository userRepository,
                           RoleService roleService,
                           ModelMapper modelMapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public void register(UserRegistrationModel registrationModel) {

    }
}
