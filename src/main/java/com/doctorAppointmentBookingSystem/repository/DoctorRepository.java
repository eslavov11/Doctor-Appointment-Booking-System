package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Edi on 16-Apr-17.
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Doctor findOneByUserId(long userId);

    List<Doctor> findAllByOrderByFirstNameAscLastName();
}
