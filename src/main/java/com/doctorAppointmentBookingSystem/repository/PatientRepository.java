package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Edi on 16-Apr-17.
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findOneByUserId(long userId);

    List<Patient> findAllByDoctorId(long doctorId);

    Page<Patient> findAllByDoctorIdOrderByDateOfBirthDesc(long doctorId, Pageable pageable);
}
