package com.doctorAppointmentBookingSystem.model.viewModel;

public class DoctorSelectViewModel {
    private long id;

    private String firstName;

    private String lastName;

    private String settlePointName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSettlePointName() {
        return settlePointName;
    }

    public void setSettlePointName(String settlePointName) {
        this.settlePointName = settlePointName;
    }
}
