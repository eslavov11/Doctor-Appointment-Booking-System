package com.doctorAppointmentBookingSystem.service;

import com.doctorAppointmentBookingSystem.entity.SettlePoint;

import java.util.List;

/**
 * Created by Edi on 16-Apr-17.
 */
public interface SettlePointService {
    List<SettlePoint> getAll();

    SettlePoint getById(long id);
}
