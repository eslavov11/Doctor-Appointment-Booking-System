package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.model.viewModel.DoctorViewModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.SettlePointService;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Edi on 04-May-17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DoctorController.class)
@ActiveProfiles("test")
@EnableSpringDataWebSupport
public class DoctorControllerTest {
    private static final long DOCTOR_ID = 1;
    private static final String FIRST_NAME = "Georgi";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DoctorService doctorService;

    @MockBean
    private SettlePointService settlePointService;

    @Before
    public void setUp() throws Exception {
        //Arrange
        DoctorViewModel doctorViewModel = new DoctorViewModel();
        doctorViewModel.setId(DOCTOR_ID);
        doctorViewModel.setFirstName(FIRST_NAME);
        when(this.doctorService.getViewModelById(DOCTOR_ID)).thenReturn(doctorViewModel);
    }

    @Test
    public void showDoctorGivenValidDoctorModel_ShouldReturnOkStatus() throws Exception {
        //Act
        this.mvc
                .perform(get("/doctor/" + DOCTOR_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void showDoctorGivenValidDoctorModel_ShouldReturnDoctorPage() throws Exception {
        //Act
        this.mvc
                .perform(get("/doctor/" + DOCTOR_ID))
                .andExpect(view().name("doctor/doctor"));
    }

    @Test
    public void showDoctorGivenValidDoctorModel_ShouldSetModel() throws Exception {
        //Act
        this.mvc
                .perform(get("/doctor/" + DOCTOR_ID))
                .andExpect(model().attribute("doctorViewModel", hasProperty("id", is(DOCTOR_ID))))
                .andExpect(model().attribute("doctorViewModel", hasProperty("firstName", is(FIRST_NAME))))
                .andExpect(model().attribute("doctorViewModel", hasProperty("lastName", IsNull.nullValue(String.class))));
    }

    @Test
    public void showDoctorGivenValidDoctorModel_ShouldCallServiceOnce() throws Exception {
        //Act
        this.mvc
                .perform(get("/doctor/" + DOCTOR_ID));
        verify(this.doctorService, times(1)).getViewModelById(DOCTOR_ID);
        verifyNoMoreInteractions(this.doctorService);
    }
}