package com.example.projectcuoiky.model;

import java.io.Serializable;

public class Subject implements Serializable {
    private int id;
    private String soTin;
    private String tenMon,phong,giaoVien;

    public Subject() {
    }

    public Subject(int id, String tenMon,String soTin, String phong, String giaoVien) {
        this.id = id;
        this.soTin = soTin;
        this.tenMon = tenMon;
        this.phong = phong;
        this.giaoVien = giaoVien;
    }

    public Subject(String tenMon,String soTin, String phong, String giaoVien) {
        this.soTin = soTin;
        this.tenMon = tenMon;
        this.phong = phong;
        this.giaoVien = giaoVien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoTin() {
        return soTin;
    }

    public void setSoTin(String soTin) {
        this.soTin = soTin;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getGiaoVien() {
        return giaoVien;
    }

    public void setGiaoVien(String giaoVien) {
        this.giaoVien = giaoVien;
    }
}
