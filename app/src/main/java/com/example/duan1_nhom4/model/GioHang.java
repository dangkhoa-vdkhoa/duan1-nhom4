package com.example.duan1_nhom4.model;

import java.io.Serializable;

public class GioHang implements Serializable {
    public String ten;
    public String hinh;

    public String gia;
    public String soluong;
    public String id;
    public int trangThai;

    public GioHang() {
    }

    public GioHang(String hinh,String ten, String gia, String soluong, String id, int trangThai) {
        this.ten = ten;
        this.hinh = hinh;
        this.gia = gia;
        this.soluong = soluong;
        this.id = id;
        this.trangThai = trangThai;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
