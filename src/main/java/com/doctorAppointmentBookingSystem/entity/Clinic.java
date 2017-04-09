package com.doctorAppointmentBookingSystem.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Edi on 06-Apr-17.
 */
@Entity
@Table(name = "clinics")
public class Clinic implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "settle_point_id")
    private SettlePoint settlePoint;

    private String address;

    private String telephone;

    private String website;

    private String description;

    public Clinic() {}

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

    public SettlePoint getSettlePoint() {
        return settlePoint;
    }

    public void setSettlePoint(SettlePoint settlePoint) {
        this.settlePoint = settlePoint;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}