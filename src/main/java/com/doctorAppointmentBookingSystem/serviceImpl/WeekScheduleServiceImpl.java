package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.entity.WeekSchedule;
import com.doctorAppointmentBookingSystem.model.bindingModel.WeekScheduleModel;
import com.doctorAppointmentBookingSystem.repository.WeekScheduleRepository;
import com.doctorAppointmentBookingSystem.service.WeekScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Edi on 22-Apr-17.
 */
@Service
public class WeekScheduleServiceImpl implements WeekScheduleService {
    private WeekScheduleRepository weekScheduleRepository;

    private ModelMapper modelMapper;

    @Autowired
    public WeekScheduleServiceImpl(WeekScheduleRepository weekScheduleRepository, ModelMapper modelMapper) {
        this.weekScheduleRepository = weekScheduleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public WeekSchedule create(WeekScheduleModel weekScheduleModel) {
        WeekSchedule daySchedule = this.modelMapper.map(weekScheduleModel, WeekSchedule.class);

        return this.weekScheduleRepository.saveAndFlush(daySchedule);
    }
}
