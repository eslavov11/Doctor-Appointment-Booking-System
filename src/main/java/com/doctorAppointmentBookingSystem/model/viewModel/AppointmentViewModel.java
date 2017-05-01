package com.doctorAppointmentBookingSystem.model.viewModel;

import java.util.Date;

/**
 * Created by Edi on 30-Apr-17.
 */
public class AppointmentViewModel {
    private long id;

    private DoctorSelectViewModel doctorSelectViewModel;

    private PatientBasicViewModel patientBasicViewModel;

    private Date date;

    private String type;

    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DoctorSelectViewModel getDoctorSelectViewModel() {
        return doctorSelectViewModel;
    }

    public void setDoctorSelectViewModel(DoctorSelectViewModel doctorSelectViewModel) {
        this.doctorSelectViewModel = doctorSelectViewModel;
    }

    public PatientBasicViewModel getPatientBasicViewModel() {
        return patientBasicViewModel;
    }

    public void setPatientBasicViewModel(PatientBasicViewModel patientBasicViewModel) {
        this.patientBasicViewModel = patientBasicViewModel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
