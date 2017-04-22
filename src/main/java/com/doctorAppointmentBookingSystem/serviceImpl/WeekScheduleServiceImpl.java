package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.entity.DayOfWeek;
import com.doctorAppointmentBookingSystem.entity.DaySchedule;
import com.doctorAppointmentBookingSystem.entity.WeekSchedule;
import com.doctorAppointmentBookingSystem.model.bindingModel.DayScheduleModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.EditDayScheduleModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.EditWeekScheduleModel;
import com.doctorAppointmentBookingSystem.model.bindingModel.WeekScheduleModel;
import com.doctorAppointmentBookingSystem.repository.WeekScheduleRepository;
import com.doctorAppointmentBookingSystem.service.DayScheduleService;
import com.doctorAppointmentBookingSystem.service.WeekScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;

/**
 * Created by Edi on 22-Apr-17.
 */
@Service
public class WeekScheduleServiceImpl implements WeekScheduleService {
    private WeekScheduleRepository weekScheduleRepository;

    private ModelMapper modelMapper;

    private DayScheduleService dayScheduleService;

    @Autowired
    public WeekScheduleServiceImpl(WeekScheduleRepository weekScheduleRepository, ModelMapper modelMapper,
                                   DayScheduleService dayScheduleService) {
        this.weekScheduleRepository = weekScheduleRepository;
        this.modelMapper = modelMapper;
        this.dayScheduleService = dayScheduleService;
    }

    @Override
    public WeekSchedule createDefault() {
        WeekSchedule weekSchedule = this.weekScheduleRepository.saveAndFlush(new WeekSchedule());

        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            DayScheduleModel dayScheduleModel = new DayScheduleModel();
            dayScheduleModel.setDayOfWeek(dayOfWeek.toString());
            dayScheduleModel.setStartTime(new Time(0L));
            dayScheduleModel.setEndTime(new Time(0L));
            dayScheduleModel.setWeekSchedule(weekSchedule);

            this.dayScheduleService.create(dayScheduleModel);
        }

        return weekSchedule;
    }

    @Override
    public EditWeekScheduleModel getById(long id) {
        WeekSchedule weekSchedule = this.weekScheduleRepository.findOne(id);

        EditWeekScheduleModel weekScheduleModel = this.modelMapper.map(weekSchedule, EditWeekScheduleModel.class);

        return weekScheduleModel;
    }

    @Override
    public void save(EditWeekScheduleModel editWeekScheduleModel) {
        for (EditDayScheduleModel editDayScheduleModel : editWeekScheduleModel.getEditDayScheduleModels()) {
            this.dayScheduleService.save(editDayScheduleModel);
        }
    }
}
