package com.example.outpass2;

class IncomingStudents {

    String Date,Roll,Time;


    public IncomingStudents() {
    }

    public IncomingStudents(String date, String roll, String time) {
        Date = date;
        Roll = roll;
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getRoll() {
        return Roll;
    }

    public void setRoll(String roll) {
        Roll = roll;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
