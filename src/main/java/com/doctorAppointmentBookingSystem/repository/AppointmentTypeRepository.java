package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Edi on 17-Apr-17.
 */
@Repository
public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Long> {
    List<AppointmentType> findAllByOrderByName();
}
