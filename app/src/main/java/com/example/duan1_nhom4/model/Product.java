package com.example.duan1_nhom4.model;


import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Product implements Serializable {
    public String ten;
    public String hinh;


    public String gia;
    public String mota;

    public int numberOder;
    public int trangThai;

    public String id;

    public Product(String hinh, String ten, String gia, String mota, String id) {
        this.ten = ten;
        this.hinh = hinh;
        this.gia = gia;
        this.mota = mota;
        this.numberOder = numberOder;
        this.id = id;
    }

    public Product(String ten, String gia, String mota, String hinh, int numberOder) {
        this.ten = ten;
        this.hinh = hinh;
        this.gia = gia;
        this.mota = mota;
        this.numberOder = numberOder;
    }

    public Product(String hinh, String ten, String gia, String mota) {
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

    public int getNumberOder() {
        return numberOder;
    }

    public void setNumberOder(int numberOder) {
        this.numberOder = numberOder;
    }
}
