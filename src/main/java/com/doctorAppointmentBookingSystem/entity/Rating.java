package com.doctorAppointmentBookingSystem.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Edi on 06-Apr-17.
 */
@Entity
@Table(name = "ratings")
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int rate;

    private String comment;

    private Patient ratingPatient;

    @Column(name = "date", insertable = false, updatable = false)
    private Date date;

    public Rating() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Patient getRatingPatient() {
        return ratingPatient;
    }

    public void setRatingPatient(Patient ratingPatient) {
        this.ratingPatient = ratingPatient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
