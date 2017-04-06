package com.doctorAppointmentBookingSystem.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Edi on 06-Apr-17.
 */
@Entity
@Table(name = "appointments")
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Appointment() {}
}
