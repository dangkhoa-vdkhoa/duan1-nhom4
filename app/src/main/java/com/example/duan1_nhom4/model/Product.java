package com.example.duan1_nhom4.model;


import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Product {
    public String ten;
    public String hinh;

    public String gia;
    public String mota;


    public Product(String hinh, String ten,  String gia, String mota) {
        this.hinh = hinh;
        this.ten = ten;
        this.gia = gia;
        this.mota = mota;
    }

    public Product(String hinh, String ten, String gia) {
        this.ten = ten;
        this.hinh = hinh;
        this.gia = gia;
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

    public Product() {
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
