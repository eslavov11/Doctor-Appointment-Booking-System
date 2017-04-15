package com.doctorAppointmentBookingSystem.serviceImpl;

/**
 * Created by Edi on 16-Apr-17.
 */

import com.doctorAppointmentBookingSystem.entity.Role;
import com.doctorAppointmentBookingSystem.repository.RoleRepository;
import com.doctorAppointmentBookingSystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private static final String DEFAULT_ROLE = "ROLE_USER";

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getDefaultRole() {
        return this.roleRepository.findOneByAuthority(DEFAULT_ROLE);
    }

    @Override
    public Role getRoleByAuthority(String authority) {
        return this.roleRepository.findOneByAuthority(authority);
    }
}
