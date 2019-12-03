package com.example.outpass2;



public class model_outside {

    private String name,roll,going,purpose,date,time,vehicle;

    public model_outside(String name, String roll, String going, String purpose, String date, String time, String vehicle) {
        this.going = going;
        this.purpose = purpose;
        this.date = date;
        this.time = time;
        this.vehicle = vehicle;
        this.name=name;
        this.roll=roll;
    }

    public String getName() {
        return name;
    }

    public String getRoll() {
        return roll;
    }

    public String getGoing() {
        return going;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public void setGoing(String going) {
        this.going = going;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getTime() {
        return time;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getVehicle() {
        return vehicle;
    }
}
