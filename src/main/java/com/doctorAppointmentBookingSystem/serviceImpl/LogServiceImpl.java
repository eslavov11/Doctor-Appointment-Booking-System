package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.entity.Log;
import com.doctorAppointmentBookingSystem.entity.SettlePoint;
import com.doctorAppointmentBookingSystem.model.viewModel.SettlePointViewModel;
import com.doctorAppointmentBookingSystem.repository.LogRepository;
import com.doctorAppointmentBookingSystem.repository.SettlePointRepository;
import com.doctorAppointmentBookingSystem.service.LogService;
import com.doctorAppointmentBookingSystem.service.SettlePointService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edi on 04-May-17.
 */
@Service
public class LogServiceImpl implements LogService {
    private LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void save(Log log) {
        this.logRepository.saveAndFlush(log);
    }
}
