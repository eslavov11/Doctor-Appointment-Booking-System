package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.entity.SettlePoint;
import com.doctorAppointmentBookingSystem.model.viewModel.SettlePointViewModel;

import java.util.List;

/**
 * Created by Edi on 16-Apr-17.
 */
public interface SettlePointService {
    List<SettlePointViewModel> getAll();

    SettlePoint getById(long id);
}
