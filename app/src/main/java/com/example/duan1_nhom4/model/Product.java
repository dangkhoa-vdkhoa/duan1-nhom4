package com.example.duan1_nhom4.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Product {
    private String ten;

    public Product(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String name) {
        this.ten = ten;
    }
}
