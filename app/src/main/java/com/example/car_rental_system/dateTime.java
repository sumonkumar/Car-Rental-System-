package com.example.car_rental_system;

public class dateTime {
    private String rDate;
    private String rTime;

    public dateTime() {
    }

    public dateTime(String rDate, String rTime) {
        this.rDate = rDate;
        this.rTime = rTime;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }
}