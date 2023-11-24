package com.example.duan1_nhom4.model;

public class GioHang {
    public String ten;
    public String hinh;

    public String gia;
    public String soluong;

    public GioHang() {
    }

    public GioHang(String hinh, String ten, String gia, String soluong) {
        this.ten = ten;
        this.hinh = hinh;
        this.gia = gia;
        this.soluong = soluong;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
