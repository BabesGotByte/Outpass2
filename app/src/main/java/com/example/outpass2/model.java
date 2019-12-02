package com.example.outpass2;

import android.media.Image;

public class model {
    private String goingto, datetime, purpose;
    Image i;

    public String getGoingto() {
        return goingto;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getPurpose() {
        return purpose;
    }

    public Image getI() {
        return i;
    }

    public void setGoingto(String goingto) {
        this.goingto = goingto;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setI(Image i) {
        this.i = i;
    }
}
