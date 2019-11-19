package com.example.outpass2;

public class OutpassInfo {
    private String going,purpose,date,time,vehicle;
    private FirstTimeRegistration firstTimeRegistration;
    private String status;

    public OutpassInfo() {
    }


    public OutpassInfo(String going, String purpose, String date, String time, String vehicle, FirstTimeRegistration firstTimeRegistration, String status) {
        this.going = going;
        this.purpose = purpose;
        this.date = date;
        this.time = time;
        this.vehicle = vehicle;
        this.firstTimeRegistration = firstTimeRegistration;
        this.status = status;
    }

    public String getGoing() {
        return going;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setGoing(String going) {
        this.going = going;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public FirstTimeRegistration getFirstTimeRegistration() {
        return firstTimeRegistration;
    }

    public void setFirstTimeRegistration(FirstTimeRegistration firstTimeRegistration) {
        this.firstTimeRegistration = firstTimeRegistration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OutpassInfo{" +
                "going='" + going + '\'' +
                ", purpose='" + purpose + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", vehicle='" + vehicle + '\'' +
                ", firstTimeRegistration=" + firstTimeRegistration.toString() +
                '}';
    }
}
