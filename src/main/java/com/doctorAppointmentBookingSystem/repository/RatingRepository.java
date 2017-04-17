package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Edi on 17-Apr-17.
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
