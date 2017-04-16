package com.doctorAppointmentBookingSystem.serviceImpl;

import com.doctorAppointmentBookingSystem.entity.SettlePoint;
import com.doctorAppointmentBookingSystem.repository.SettlePointRepository;
import com.doctorAppointmentBookingSystem.service.SettlePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Edi on 16-Apr-17.
 */
@Service
public class SettlePointServiceImpl implements SettlePointService {
    private SettlePointRepository settlePointRepository;

    @Autowired
    public SettlePointServiceImpl(SettlePointRepository settlePointRepository) {
        this.settlePointRepository = settlePointRepository;
    }

    @Override
    public List<SettlePoint> getAll() {
        return this.settlePointRepository.findAll();
    }

    @Override
    public SettlePoint getById(long id) {
        return this.settlePointRepository.findOne(id);
    }
}
