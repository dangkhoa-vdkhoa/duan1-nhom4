package com.example.duan1_nhom4.model;

public class Username {

    private String hoten;

    private String email;

    private String sdt;

    private String diachi;

    public Username() {
    }

    public Username(String hoten, String email) {
        this.hoten = hoten;
        this.email = email;
    }

    public void Username2(String sdt, String diachi) {
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
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
