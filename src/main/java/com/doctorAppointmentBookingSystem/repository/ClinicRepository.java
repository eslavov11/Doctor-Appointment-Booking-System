package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Edi on 17-Apr-17.
 */
@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
