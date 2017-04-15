package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Edi on 16-Apr-17.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findOneByAuthority(String authority);
}
