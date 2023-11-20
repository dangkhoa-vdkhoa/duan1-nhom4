package com.example.duan1_nhom4.model;


import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class Product {
    public String ten;
    public int hinh;


    public Product(String ten, int hinh) {
        this.ten = ten;
        this.hinh = hinh;
    }

    public Product() {
    }


}
