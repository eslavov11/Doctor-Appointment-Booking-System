package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Edi on 17-Apr-17.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
