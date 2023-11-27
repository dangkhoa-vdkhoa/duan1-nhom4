package com.example.duan1_nhom4.model;

import java.io.Serializable;

public class User implements Serializable {
    String tennhanhang,sodienthoai,diachi,ten,gia,sosp,img;
    public int trangThai;


    public User(String tennhanhang, String sodienthoai, String diachi, String ten, String gia, String sosp, String img) {
        this.tennhanhang = tennhanhang;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.ten = ten;
        this.gia = gia;
        this.sosp = sosp;
        this.img = img;
    }

    public User(String tennhanhang, String sodienthoai, String diachi, String ten, String gia, String sosp, String img, int trangThai) {
        this.tennhanhang = tennhanhang;
        this.sodienthoai = sodienthoai;
        this.diachi = diachi;
        this.ten = ten;
        this.gia = gia;
        this.sosp = sosp;
        this.img = img;
        this.trangThai = trangThai;
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

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSosp() {
        return sosp;
    }

    public void setSosp(String sosp) {
        this.sosp = sosp;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
