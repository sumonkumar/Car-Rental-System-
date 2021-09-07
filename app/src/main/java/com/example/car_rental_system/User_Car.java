package com.example.car_rental_system;

public class User_Car {

    private String carname;
    private String carnumbernumberId;
    private String carvalue;
    private String uID;

    public User_Car() {

    }

    public User_Car(String carname, String carnumbernumberId, String carvalue, String uID) {
        this.carname = carname;
        this.carnumbernumberId = carnumbernumberId;
        this.carvalue = carvalue;
        this.uID = uID;
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

    public String getuID() {
        return uID;
    }

    public void setuID(String uID) {
        this.uID = uID;
    }
}