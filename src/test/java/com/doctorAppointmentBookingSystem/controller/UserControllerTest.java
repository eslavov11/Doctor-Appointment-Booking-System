package com.doctorAppointmentBookingSystem.controller;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Edi on 04-May-17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ActiveProfiles("test")
public class UserControllerTest {

}