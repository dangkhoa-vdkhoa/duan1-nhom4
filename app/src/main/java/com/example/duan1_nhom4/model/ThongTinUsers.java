package com.example.duan1_nhom4.model;

public class ThongTinUsers {
    public String sdt;
    public String diachi;

    public ThongTinUsers() {
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

    public ThongTinUsers(String sdt, String diachi) {
        this.sdt = sdt;
        this.diachi = diachi;
    }
}
