package com.example.duan1_nhom4.model;


import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Product {
    public String ten;
    public String hinh;


    public Product(String hinh, String ten) {
        this.ten = ten;
        this.hinh = hinh;
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


}
