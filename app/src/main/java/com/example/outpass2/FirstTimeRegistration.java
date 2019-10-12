package com.example.outpass2;

public class FirstTimeRegistration {

    private String name,contact,room,hostel,gname,gnumber;
    private Boolean accepted;

    public FirstTimeRegistration() {
    }

    public FirstTimeRegistration(String name, String contact, String room, String hostel, String gname, String gnumber, Boolean accepted) {
        this.name = name;
        this.contact = contact;
        this.room = room;
        this.hostel = hostel;
        this.gname = gname;
        this.gnumber = gnumber;
        this.accepted = accepted;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public void setGnumber(String gnumber) {
        this.gnumber = gnumber;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getRoom() {
        return room;
    }

    public String getHostel() {
        return hostel;
    }

    public String getGname() {
        return gname;
    }

    public String getGnumber() {
        return gnumber;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
