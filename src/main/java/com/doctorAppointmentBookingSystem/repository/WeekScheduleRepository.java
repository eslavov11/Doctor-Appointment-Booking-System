package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.WeekSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Edi on 22-Apr-17.
 */
@Repository
public interface WeekScheduleRepository extends JpaRepository<WeekSchedule, Long> {
}
