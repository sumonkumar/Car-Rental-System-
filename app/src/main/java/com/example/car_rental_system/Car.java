package com.example.car_rental_system;

public class Car {

    private String carname;
    private String carnumbernumberId;
    private String carvalue;

    public Car() {

    }

    public String getCarname() {
        return carname;
    }

    public void setCarname(String carname) {
        this.carname = carname;
    }

    public String getCarnumbernumberId() {
        return carnumbernumberId;
    }

    public void setCarnumbernumberId(String carnumbernumberId) {
        this.carnumbernumberId = carnumbernumberId;
    }

    public String getCarvalue() {
        return carvalue;
    }

    public void setCarvalue(String carvalue) {
        this.carvalue = carvalue;
    }

    public Car(String carname, String carnumbernumberId, String carvalue) {
        this.carname = carname;
        this.carnumbernumberId = carnumbernumberId;
        this.carvalue = carvalue;
    }
}