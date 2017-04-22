package com.doctorAppointmentBookingSystem.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Edi on 08-Apr-17.
 */
@Entity
@Table(name = "day_schedules")
public class DaySchedule implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private Time startTime;

    private Time endTime;

    @ManyToOne
    @JoinColumn(name = "week_schedule_id")
    private WeekSchedule weekSchedule;

    public DaySchedule() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public WeekSchedule getWeekSchedule() {
        return weekSchedule;
    }

    public void setWeekSchedule(WeekSchedule weekSchedule) {
        this.weekSchedule = weekSchedule;
    }
}
