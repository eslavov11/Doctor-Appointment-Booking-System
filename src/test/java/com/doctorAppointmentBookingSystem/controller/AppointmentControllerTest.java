package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.SettlePoint;
import com.doctorAppointmentBookingSystem.entity.User;
import com.doctorAppointmentBookingSystem.model.viewModel.AppointmentViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorSelectViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorViewModel;
import com.doctorAppointmentBookingSystem.service.*;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Edi on 05-May-17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AppointmentController.class)
@ActiveProfiles("test")
@EnableSpringDataWebSupport
public class AppointmentControllerTest {
    private static final long USER_ID = 0;
    private static final long APPOINTMENT_ID = 1;
    private static final String DESCRIPTION = "Pain in the knees";
    private static final String DATE_MM_DD_YYYY = "01/01/2000";

    private static Date DATE;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private AppointmentTypeService appointmentTypeService;

    @MockBean
    private PatientService patientService;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private SettlePointService settlePointService;

    @MockBean
    Authentication principal;

    @Before
    public void setUp() throws Exception {
        //Arrange
        AppointmentViewModel appointmentViewModel = new AppointmentViewModel();
        appointmentViewModel.setId(APPOINTMENT_ID);

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        DATE = formatter.parse(DATE_MM_DD_YYYY);

        DoctorSelectViewModel doctorSelectViewModel = new DoctorSelectViewModel();
        doctorSelectViewModel.setId(0);

        appointmentViewModel.setDate(DATE);
        appointmentViewModel.setDescription(DESCRIPTION);
        appointmentViewModel.setDoctorSelectViewModel(doctorSelectViewModel);
        when(this.appointmentService.getById(APPOINTMENT_ID)).thenReturn(appointmentViewModel);


        User user = new User();
        user.setId(USER_ID);

        when(this.principal.getPrincipal()).thenReturn(user);
    }

    @Test
    public void showAppointmentGivenValidModel_ShouldReturnOkStatus() throws Exception {
        //Act
        this.mvc
                .perform(get("/appointment/getForDate?date=" + DATE_MM_DD_YYYY))
                .andExpect(status().isOk());
    }

    @Test
    public void showAppointmentGivenValidModel_ShouldCallServiceOnce() throws Exception {
        //Act
        this.mvc
                .perform(get("/appointment/getForDate?date=" + DATE_MM_DD_YYYY));
        verify(this.appointmentService, times(1)).getAllForDateAndDoctor(DATE, 0);
        verifyNoMoreInteractions(this.appointmentService);
    }

   /* @Test
    public void showDoctorAppointmentGivenValidModel_ShouldReturnOkStatus() throws Exception {
        //Act
        this.mvc
                .perform(get("/appointment/doctor/" + APPOINTMENT_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void showDoctorAppointmentGivenValidModel_ShouldCallServiceOnce() throws Exception {
        //Act
        this.mvc
                .perform(get("/appointment/doctor/" + APPOINTMENT_ID));
        verify(this.appointmentService, times(1)).getById(APPOINTMENT_ID);
        verifyNoMoreInteractions(this.appointmentService);
    }


    @Test
    public void showDoctorAppointmentGivenValidModel_ShouldReturnPage() throws Exception {
        //Act
        this.mvc
                .perform(get("/appointment/doctor/" + APPOINTMENT_ID))
                .andExpect(view().name("appointment/appointment"));
    }

    @Test
    public void showDoctorAppointmentGivenValidModel_ShouldSetModel() throws Exception {
        //Act
        this.mvc
                .perform(get("/appointment/doctor/" + APPOINTMENT_ID))
                .andExpect(model().attribute("appointmentViewModel", hasProperty("id", is(APPOINTMENT_ID))))
                .andExpect(model().attribute("appointmentViewModel", hasProperty("description", is(DESCRIPTION))));
    }*/
}