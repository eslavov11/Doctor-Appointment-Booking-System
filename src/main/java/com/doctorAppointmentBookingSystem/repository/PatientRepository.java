package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Edi on 16-Apr-17.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
