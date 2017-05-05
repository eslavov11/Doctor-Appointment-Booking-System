package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.model.viewModel.DoctorViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.PatientViewModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.PatientService;
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

import java.util.Date;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by Edi on 05-May-17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PatientController.class)
@ActiveProfiles("test")
@EnableSpringDataWebSupport
public class PatientControllerTest {
    private static final long PATIENT_ID = 1;
    private static final String FIRST_NAME = "Georgi";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private DoctorService doctorService;

    @Before
    public void setUp() throws Exception {
        //Arrange
        PatientViewModel patientViewModel = new PatientViewModel();
        patientViewModel.setId(PATIENT_ID);
        patientViewModel.setDateOfBirth(new Date());
        patientViewModel.setFirstName(FIRST_NAME);
        when(this.patientService.getById(PATIENT_ID)).thenReturn(patientViewModel);
    }

    @Test
    public void showPatientGivenValidModel_ShouldReturnOkStatus() throws Exception {
        //Act
        this.mvc
                .perform(get("/patient/" + PATIENT_ID))
                .andExpect(status().isOk());
    }

    @Test
    public void showPatientGivenValidModel_ShouldReturnPage() throws Exception {
        //Act
        this.mvc
                .perform(get("/patient/" + PATIENT_ID))
                .andExpect(view().name("patient/patient"));
    }

    @Test
    public void showPatientGivenValidModel_ShouldSetModel() throws Exception {
        //Act
        this.mvc
                .perform(get("/patient/" + PATIENT_ID))
                .andExpect(model().attribute("patientViewModel", hasProperty("id", is(PATIENT_ID))))
                .andExpect(model().attribute("patientViewModel", hasProperty("firstName", is(FIRST_NAME))))
                .andExpect(model().attribute("patientViewModel", hasProperty("lastName", IsNull.nullValue(String.class))));
    }

    @Test
    public void showPatientGivenValidModel_ShouldCallServiceOnce() throws Exception {
        //Act
        this.mvc
                .perform(get("/patient/" + PATIENT_ID));
        verify(this.patientService, times(1)).getById(PATIENT_ID);
        verifyNoMoreInteractions(this.patientService);
    }
}