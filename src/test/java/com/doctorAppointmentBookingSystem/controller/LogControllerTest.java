package com.doctorAppointmentBookingSystem.controller;

import com.doctorAppointmentBookingSystem.entity.Log;
import com.doctorAppointmentBookingSystem.model.viewModel.DoctorViewModel;
import com.doctorAppointmentBookingSystem.model.viewModel.LogViewModel;
import com.doctorAppointmentBookingSystem.service.DoctorService;
import com.doctorAppointmentBookingSystem.service.LogService;
import com.doctorAppointmentBookingSystem.service.SettlePointService;
import org.hamcrest.core.IsNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasItemInArray;
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
@WebMvcTest(LogController.class)
@ActiveProfiles("test")
@EnableSpringDataWebSupport
public class LogControllerTest {
    private static final long LOG_ID = 1;
    private static final String TEXT = "[class DoctorController : getDoctorPage]";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LogService logService;

    @Before
    public void setUp() throws Exception {
        //Arrange
        LogViewModel logViewModel = new LogViewModel();
        logViewModel.setId(LOG_ID);
        logViewModel.setDate(new Date());
        logViewModel.setText(TEXT);

        List<LogViewModel> logList = new ArrayList<>();
        logList.add(logViewModel);

        Page<LogViewModel> log = new PageImpl<>(logList);

        Pageable pageable = new PageRequest(1,1);
        when(this.logService.getAll(any(Pageable.class))).thenReturn(log);
    }

    @Test
    public void showGivenValidModel_ShouldReturnOkStatus() throws Exception {
        //Act
        this.mvc
                .perform(get("/log"))
                .andExpect(status().isOk());
    }

    @Test
    public void showGivenValidModel_ShouldReturnPage() throws Exception {
        //Act
        this.mvc
                .perform(get("/log"))
                .andExpect(view().name("admin/log"));
    }

    @Test
    public void showGivenValidModel_ShouldCallServiceOnce() throws Exception {
        //Act
        this.mvc
                .perform(get("/log"));
        verify(this.logService, times(1)).getAll(any(Pageable.class));
        verifyNoMoreInteractions(this.logService);
    }
}