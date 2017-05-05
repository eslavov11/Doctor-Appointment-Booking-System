package com.doctorAppointmentBookingSystem.repository;

import com.doctorAppointmentBookingSystem.entity.Appointment;
import com.doctorAppointmentBookingSystem.entity.Doctor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Edi on 04-May-17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class AppointmentRepositoryTest {
    private static final String DESCRIPTION = "Pain in the knees";

    private static Calendar calendarDate = Calendar.getInstance();
    private static Calendar calendarBeforeDate = Calendar.getInstance();
    private static Calendar calendarAfterDate = Calendar.getInstance();

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Before
    public void setUp() throws Exception {
        //Arrange
        Appointment appointment = new Appointment();
        appointment.setDescription(DESCRIPTION);

        calendarDate.set(2000, 0, 1);
        calendarBeforeDate.set(1999, 0, 1);
        calendarAfterDate.set(2001, 0, 1);
        appointment.setDate(calendarDate.getTime());
        this.em.persist(appointment);
    }

    @Test
    public void findAppointmentsBetweenDates_ShouldReturnOne() throws Exception {
        //Act
        List<Appointment> appointments = this.appointmentRepository
                .findAllBetweenDates(calendarBeforeDate.getTime(), calendarAfterDate.getTime());

        //Assert
        assertEquals(1, appointments.size());
    }

    @Test
    public void findAppointmentsBetweenDates_ShouldMatchDescription() throws Exception {
        //Act
        List<Appointment> appointments = this.appointmentRepository
                .findAllBetweenDates(calendarBeforeDate.getTime(), calendarAfterDate.getTime());

        //Assert
        assertEquals(DESCRIPTION, appointments.get(0).getDescription());
    }

    @Test
    public void findAppointmentsBetweenDates_BetweenDatesSwitched_ShouldReturnNone() throws Exception {
        //Act
        List<Appointment> appointments = this.appointmentRepository
                .findAllBetweenDates(calendarAfterDate.getTime(), calendarBeforeDate.getTime());

        //Assert
        assertEquals(0, appointments.size());
    }
}