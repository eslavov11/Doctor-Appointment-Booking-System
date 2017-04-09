package com.doctorAppointmentBookingSystem.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Edi on 06-Apr-17.
 */
@Entity
@Table(name = "doctor_specialties")
public class DoctorSpecialty implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    public DoctorSpecialty() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
