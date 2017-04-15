package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Edi on 06-Apr-17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);
}
