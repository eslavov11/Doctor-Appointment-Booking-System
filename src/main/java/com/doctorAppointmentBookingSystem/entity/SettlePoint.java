package com.doctorAppointmentBookingSystem.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Edi on 06-Apr-17.
 */
@Entity
@Table(name = "settle_points")
public class SettlePoint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public SettlePoint() {}
}
