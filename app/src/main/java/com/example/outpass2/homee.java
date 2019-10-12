package com.example.outpass2;

public class homee {
    private String going,purpose,date,time,vehicle;

    public homee(String going, String purpose, String date, String time, String vehicle) {
        this.going = going;
        this.purpose = purpose;
        this.date = date;
        this.time = time;
        this.vehicle = vehicle;
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
}
