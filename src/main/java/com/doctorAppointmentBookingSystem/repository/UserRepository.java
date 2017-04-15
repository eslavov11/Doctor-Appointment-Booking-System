package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Edi on 06-Apr-17.
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
