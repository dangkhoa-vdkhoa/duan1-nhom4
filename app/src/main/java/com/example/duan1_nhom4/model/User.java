package com.example.duan1_nhom4.model;

import java.io.Serializable;

public class User implements Serializable {
    String tennhanhang,sodienthoai,diachi;

    public User(String tennhanhang, String sodienthoai, String diachi) {
        this.tennhanhang = tennhanhang;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
    }

    public User() {
    }

    public String getTennhanhang() {
        return tennhanhang;
    }

    public void setTennhanhang(String tennhanhang) {
        this.tennhanhang = tennhanhang;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
