package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.entity.Role;

/**
 * Created by Edi on 16-Apr-17.
 */
public interface RoleService {
    Role getDefaultRole();

    Role getRoleByAuthority(String authority);
}
