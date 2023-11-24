package com.example.duan1_nhom4.model;

public class Username {

    private String hoten;

    private String email;

    public Username() {
    }

    public Username(String hoten, String email) {
        this.hoten = hoten;
        this.email = email;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}