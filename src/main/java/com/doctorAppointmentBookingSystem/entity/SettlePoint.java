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

    private String name;

    private String ekatte;

    private String latitude;

    private String longitude;

    public SettlePoint() {}

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

    public String getEkatte() {
        return ekatte;
    }

    public void setEkatte(String ekatte) {
        this.ekatte = ekatte;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
